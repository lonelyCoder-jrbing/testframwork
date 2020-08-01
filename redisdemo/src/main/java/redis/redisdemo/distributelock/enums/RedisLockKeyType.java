package redis.redisdemo.distributelock.enums;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:
 **/
public enum RedisLockKeyType {
    //当前对象的  toString 作为key
    ALL,
    //当前对象的  属性作为key值
    FIELD,
    ;
}
