package redis.redisdemo.distributelockbyredis.annotation;

import java.lang.annotation.*;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {

    /**
     * redis锁，重试次数-1代表无限重试
     * */
    int unLimitRetryCount = -1;

    /**
     * redis锁对应的key 会拼接此参数，用于进一步区分，避免redis的key被覆盖
     * */
    String lockKey() default "";

    /**
     * redis锁过期时间（单位：秒）
     * */
    int expireTime() default 10;

    /**
     * redis锁，加锁失败重试次数 默认30次，大约3s
     * 超过指定次数后，抛出加锁失败异常，可以由调用方自己补偿
     * @see* */
    int retryCount() default 30;

}
