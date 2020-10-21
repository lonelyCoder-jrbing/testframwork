package redis.redisdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PreHeat {
	/**
	* 预热顺序，分为高优先级和低优先级
	*/
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;
    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    enum CostTimeLevel {
        SEC_5,
        SEC_30,
    }

    String key() default "";

    /**
     * Remote Cache中有效期
     * @return
     */
    int seconds() default 600;

    String databaseIndex() default "0";

    String desc() default "";

    /**
     * 预热顺序 默认在最高优先级
     * 高优先级：基础数据，且被其他需要预热的服务所依赖
     * 低优先级：依赖其他需要预热的数据
     * @return
     */
    int order() default HIGHEST_PRECEDENCE;

    //预热时可能用到的参数，目前先支持「单个参数」
    String[] preParams() default "";

    /**
     * 控制是否写redis 默认写
     * @return
     */
    boolean redisWrite() default true;

    /**
     * 控制是否读redis 默认读
     * @return
     */
    boolean redisRead() default true;

    /**
     * 控制是否使用本地缓存 默认不开启
     *
     * @return
     */
    boolean local() default false;

    /**
     * 双开关控制是否读本地缓存 默认开 加双开关是为了预热时也收益基础缓存，否则预热速度不理想
     *
     * @return
     */
    boolean localRead() default true;

    /**
     * 双开关控制是否写本地缓存 默认开
     *
     * @return
     */
    boolean localWrite() default true;

    /**
     * 控制本地缓存对应容器的大小
     *
     * @return
     */
    int localSize() default 1;

    /**
     * 控制本地缓存过期时间 默认60s
     * @return
     */
    int localSeconds() default 60;

    /**
     * 标识当前预热项的耗时级别 默认为5s内
     * @return
     */
    CostTimeLevel costTimeLevel() default CostTimeLevel.SEC_5;
}