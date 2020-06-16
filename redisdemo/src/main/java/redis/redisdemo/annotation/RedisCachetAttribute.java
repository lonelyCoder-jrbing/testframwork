package redis.redisdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc:   redis上使用的缓存注解，仅仅支持方法
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCachetAttribute {

    /**
     * @return 缓存的key值
     * 对应的Method的返回值必须 实现 Serializable 接口
     *
     */
   String key();

    /***
     * 到期秒数
     * @return
     */

   int expireSeconds() default 20;





}
