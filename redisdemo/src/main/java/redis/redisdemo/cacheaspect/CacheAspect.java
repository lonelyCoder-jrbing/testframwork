package redis.redisdemo.cacheaspect;

import com.alibaba.fastjson.JSON;
import com.sun.javaws.CacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.redisdemo.annotation.PreHeat;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.function.Function;

/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc:https://blog.csdn.net/weixin_40292704/article/details/103870544
 **/

@Aspect
@Component
public class CacheAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CacheService redisCache;
    @Autowired
    LocalCacheService localCache;
//    @Autowired
//    SpelSupport spelSupport;

    /**
     * 默认用[前缀]_[类名]_[方法名]_[参数转换的字符串]作为缓存key
     */
    String CACHE_KEY_FORMAT = "aop_%s_%s_%s";

    @Around("@annotation(com.onepiece.cache.aspect.PreHeat)")
    public Object aroundPreHeat(ProceedingJoinPoint point) {
        return action(point, method -> new RedisParamEntity(method.getDeclaredAnnotation(PreHeat.class)));
    }

    //暂时没有搞定注解在泛型中的应用，只能通过RedisParamEntity多做了一层转换

    private Object action(ProceedingJoinPoint point, Function<Method, RedisParamEntity> template) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RedisParamEntity entity = template.apply(method);
        String cacheKey = parseKeyToCacheKey(entity.key, method, point.getArgs());
        Type resultType = method.getGenericReturnType();

        if (entity.readLocal() && entity.writeLocal()) {
            //若完全开启本地缓存 走原子操作
            return localCache.getOrElsePut((md) -> parseToLocalCacheInstanceName(method), cacheKey, entity.localSeconds, entity.localSize,
                    () -> redisAroundAction(point, resultType, entity, cacheKey));
        }

        Object result = null;
        if (entity.readLocal() && !entity.writeLocal()) {
            result = localCache.get(parseToLocalCacheInstanceName(method), cacheKey, entity.localSeconds, entity.localSize);
            if (result != null) {
                //本地命中则返回
                return result;
            }
        }
        //过一圈redis
        result = redisAroundAction(point, resultType, entity, cacheKey);

        //拿到结果后（无论是redis给的还是db给的），若开了本地缓存，则刷到本地 todo 最高会导致seconds秒的时效性损失，因此本地缓存最好先应用在时效性要求不高的场景下
        if (entity.writeLocal() && result != null) {
            localCache.set(parseToLocalCacheInstanceName(method), cacheKey, result, entity.localSeconds, entity.localSize);
        }
        return result;
    }

    /**
     * 生成缓存key，支持SPEL表达式
     *
     * @param key
     * @param method
     * @param args
     * @return
     */
    private String parseKeyToCacheKey(String key, Method method, Object[] args) {
        //若没指定key，则自动生成
        if (StringUtils.isEmpty(key)) {
            return String.format(CACHE_KEY_FORMAT, method.getDeclaringClass().getSimpleName(), method.getName(), "cachkey");
        }
//        return spelSupport.getSpelValue(key, method, args);
        return "";
    }

    /**
     * 生成本地缓存的实例名称，避免冲突
     *
     * @param method
     * @return
     */
    private String parseToLocalCacheInstanceName(Method method) {
        return String.format("%s_%s", method.getDeclaringClass().getSimpleName(), method.getName());
    }

    private Object redisAroundAction(ProceedingJoinPoint point, Type resultType, RedisParamEntity entity, String cacheKey) {
        if (entity.redisRead && redisCache.exists(cacheKey, entity.databaseIndex)) {
            return takeFromRedis(cacheKey, entity.databaseIndex, resultType);
        } else {
            if (entity.redisWrite) {
                return cacheToRedisWrapAction(cacheKey, entity, point);
            } else {
                try {
                    return point.proceed();
                } catch (Throwable throwable) {
                    throw (RuntimeException) throwable;
                }
            }
        }
    }

    private Object takeFromRedis(String key, String databaseIndex, Type returnType) {
        String json = redisCache.get(key, databaseIndex);
        if (returnType.equals(String.class)) {
            return json;
        } else {
            return JSON.parseObject(json, returnType);
        }
    }

    private Object cacheToRedisWrapAction(String cacheKey, RedisParamEntity entity, ProceedingJoinPoint point) {
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throw (RuntimeException) throwable;
        }
        if (result != null) {
            if (result instanceof String) {
                redisCache.set(cacheKey, result.toString(), entity.seconds, entity.databaseIndex);
            } else {
                redisCache.set(cacheKey, JSON.toJSONString(result), entity.seconds, entity.databaseIndex);
            }
        }
        return result;
    }

    class RedisParamEntity {
        private final String key;
        private final int seconds;
        private final String databaseIndex;
        private final boolean redisRead;
        private final boolean redisWrite;
        private final boolean needLocalCache;
        private final boolean localRead;
        private final boolean localWrite;
        private final int localSize;
        private final int localSeconds;

//        private RedisParamEntity(Cache cache) {
//            this.key = cache.key();
//            this.seconds = cache.seconds();
//            this.databaseIndex = cache.databaseIndex();
//            this.redisRead = cache.redisRead();
//            this.redisWrite = cache.redisWrite();
//            this.needLocalCache = cache.local();
//            this.localRead = cache.localRead();
//            this.localWrite = cache.locaWlrite();
//            this.localSize = cache.localSize();
//            this.localSeconds = cache.localSeconds();
//        }

        private RedisParamEntity(PreHeat preHeat) {
            this.key = preHeat.key();
            this.seconds = preHeat.seconds();
            this.databaseIndex = preHeat.databaseIndex();
            this.redisRead = preHeat.redisRead();
            this.redisWrite = preHeat.redisWrite();
            this.needLocalCache = preHeat.local();
            this.localRead = preHeat.localRead();
            this.localWrite = preHeat.localWrite();
            this.localSize = preHeat.localSize();
            this.localSeconds = preHeat.localSeconds();
        }

        protected boolean readLocal() {
            return needLocalCache && localRead;
        }

        protected boolean writeLocal() {
            return needLocalCache && localWrite;
        }
    }
}