package redis.redisdemo.cacheaspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import redis.redisdemo.annotation.RedisCachetAttribute;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc:
 **/
//开启AspectJ 自动代理模式,如果不填proxyTargetClass=true，默认为false，
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
@Order(-1)
@Aspect
public class RedisCacheAspect {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(RedisCacheAspect.class);
    /**
     * SPEL表达式解析器
     */
    private static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();

    /**
     * 获取方法参数名称发现器
     */
    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * Redis集群
     */
    @Autowired
    StringRedisTemplate redisTemplate;


    /**
     * 切面切入点
     */
    @Pointcut("@annotation(redis.redisdemo.annotation.RedisCachetAttribute)")
    public void mergeDuplicationRequest() {

    }

    /**
     * 环绕切面
     */
    @Around("mergeDuplicationRequest()")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取controller对应的方法.
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();

        //获取注解
        RedisCachetAttribute annotation = method.getAnnotation(RedisCachetAttribute.class);
        //获取缓存key的表达式,并根据上下文等数据计算表达式
        String cacheKey = parseKey(annotation.key(), proceedingJoinPoint);
        int seconds = annotation.expireSeconds();
        //先尝试从redis里获取数据(字节)
//        byte[] redisKey = cacheKey.getBytes();
//        byte[] redisValue = redisTemplate.get(redisKey);
//        byte[] redisValue = redisTemplate.opsForValue().get(cacheKey).getBytes();
        String redisValue = redisTemplate.opsForValue().get(cacheKey);
        if (redisValue != null) {
            //redis有数据,直接返回
            return toObject(redisValue);
        }

        //redis没有数据,则调用原方法,获取结果值
        Object result = proceedingJoinPoint.proceed();
        //将返回值序列化为Byte[],保存到redis
//        redisValue = toByteArray(result);
//        jedisCluster.setex(redisKey, seconds, redisValue);
        redisTemplate.opsForValue().set(cacheKey, redisValue, Duration.ofSeconds(seconds));
        return result;
    }


    /**
     * 计算spel表达式
     *
     * @param expression 表达式
     * @param context    上下文
     * @return String的缓存key
     */
    private static String parseKey(String expression, JoinPoint context) {
        //获取切入点的方法信息
        MethodSignature methodSignature = (MethodSignature) context.getSignature();
        Method method = methodSignature.getMethod();

        // 获取传入参数值
        Object[] args = context.getArgs();
        if (args == null || args.length == 0) {
            // 无参传入,直接计算表达式(无需参数上下文)
            return EXPRESSION_PARSER.parseExpression(expression).getValue(String.class);
        }

        // 获取参数名
        String[] parameterNames = PARAMETER_NAME_DISCOVERER.getParameterNames(method);
        if (parameterNames.length > args.length) {
            //由于java不允许有匿名参数,所以如果参数名多于参数值,则必为非法
            logger.error("参数值的长度少于参数名长度, 方法:{}, 参数名长度: {},参数值长度:{}", method, parameterNames.length, args.length);
            throw new IllegalArgumentException("参数传入不足");
        }

        // 将参数名与参数值放入参数上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            evaluationContext.setVariable(parameterNames[i], args[i]);
        }

        // 计算表达式(根据参数上下文)
        return EXPRESSION_PARSER.parseExpression(expression).getValue(evaluationContext, String.class);
    }

    private Object toObject(String key) {
//
//        if (bytes != null && bytes.length > 0) return (Object) new String(bytes);
        return (Object) key;
    }

    private byte[] toByteArray(Object o) {
        if (o instanceof String) {
            return ((String) o).getBytes();
        }
        return null;
    }
}
