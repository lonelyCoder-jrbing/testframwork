package redis.redisdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;
/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc:自定义redis缓存切面的注解
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

    long time() default 1;

    TimeUnit timeunit() default TimeUnit.DAYS;

}