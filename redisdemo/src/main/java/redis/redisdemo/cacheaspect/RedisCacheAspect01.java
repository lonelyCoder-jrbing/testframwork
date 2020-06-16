package redis.redisdemo.cacheaspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import redis.redisdemo.annotation.RedisCache;

import java.lang.reflect.Method;

/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc:
 **/
@Component
@Aspect
@Slf4j
public class RedisCacheAspect01 {
    @Autowired
    private RedissonClient client;
    @Around(value="@annotation(redis.redisdemo.annotation.RedisCache)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //System.out.println("环绕通知前....");
        Class clazz = joinPoint.getTarget().getClass();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        StringBuilder keyB = new StringBuilder();
        //此处的key是可以用uuid生成加密的key值。这样生成的key值可以保证唯一性
        keyB.append(clazz.getSimpleName()).append("_").append(method.getName());

        String[] argNames = null;
        Object[] argValues = null;
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        argNames = u.getParameterNames(method);
        argValues = joinPoint.getArgs();

        if(argNames.length==argValues.length){
            for (int i = 0; i < argNames.length; i++) {
                keyB.append("_").append(argValues[i]);
            }
        }
        String key = keyB.toString();
        log.info("RedisCacheAspect01====key==={}",key);
        Object obj = client.getBucket(key).get();
        log.info("RedisCacheAspect01====obj==={}",obj);
        if(obj!=null){
            return obj;
        }
        else{
            obj = joinPoint.proceed();
        }

        if(obj!=null){
            if(method.isAnnotationPresent(RedisCache.class)){
                RedisCache redisCache = method.getAnnotation(RedisCache.class);
                client.getBucket(key).set(obj, redisCache.time(), redisCache.timeunit());
            }
        }
        //System.out.println("环绕通知后....");
        return obj;
    }




}
