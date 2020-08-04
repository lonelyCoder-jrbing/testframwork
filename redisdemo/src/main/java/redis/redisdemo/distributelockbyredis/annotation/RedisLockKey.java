package redis.redisdemo.distributelockbyredis.annotation;

import redis.redisdemo.distributelockbyredis.enums.RedisLockKeyType;

import java.lang.annotation.*;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLockKey {

    String expressionKeySeparator = ",";

    RedisLockKeyType type();

    String expression() default "";


}
