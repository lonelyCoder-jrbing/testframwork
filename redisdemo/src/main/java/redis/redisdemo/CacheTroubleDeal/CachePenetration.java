package redis.redisdemo.CacheTroubleDeal;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by sumerian on 2020/4/12
 * <p>
 * desc: 缓存穿透和缓存雪崩的解决方案
 **/
@Component
@Slf4j
public class CachePenetration {

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    public static CachePenetration cachePenetration;

    /***
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
     * 并且只会被服务器调用一次，类似于Servlet的init()方法。
     * 被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。
     */
    @PostConstruct
    public void _init() {
        cachePenetration = this;
        cachePenetration.redisCacheTemplate = this.redisCacheTemplate;
    }

    public String getData(String key) throws InterruptedException {

        ReentrantLock reenLock = new ReentrantLock();
        //从Redis查询数据
        String result = cachePenetration.getDataByKV(key);
        //参数校验
        if (StringUtils.isBlank(result)) {
            try {
                //获得锁
                if (reenLock.tryLock()) {
                    //去数据库查询
                    result = cachePenetration.getDataByDB(key);
                    //校验
                    if (StringUtils.isNotBlank(result)) {
                        //插进缓存
                        cachePenetration.setDataToKV(key, result);
                    }
                } else {
                    //睡一会再拿
                    Thread.sleep(100L);
                    result = getData(key);
                }
            } finally {
                //释放锁
                reenLock.unlock();
            }
        } else {
            log.info("get data from redis ");
        }

        return result;
    }

    private void setDataToKV(String key, String result) {
        redisCacheTemplate.opsForValue().set(key, result);
    }

    private String getDataByDB(String key) {
        //数据库查询逻辑
        return "USERMESG";
    }

    private String getDataByKV(String key) {
        Serializable serializable = redisCacheTemplate.opsForValue().get(key);
        if (Objects.isNull(serializable)) {
            return null;
        }
        return (String) serializable;

    }

}
