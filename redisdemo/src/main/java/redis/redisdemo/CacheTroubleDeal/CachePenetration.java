package redis.redisdemo.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by sumerian on 2020/4/12
 * <p>
 * desc: 缓存穿透和缓存雪崩的解决方案
 **/
public class CachePenetration {

    @Autowired
    private  RedisTemplate<String, Serializable> redisCacheTemplate;

    public  String getData(String key) throws InterruptedException {

        ReentrantLock reenLock = new ReentrantLock();
        //从Redis查询数据
        String result =new CachePenetration().getDataByKV(key);
        //参数校验
        if (StringUtils.isBlank(result)) {
            try {
                //获得锁
                if (reenLock.tryLock()) {
                    //去数据库查询
                    result = new CachePenetration().getDataByDB(key);
                    //校验
                    if (StringUtils.isNotBlank(result)) {
                        //插进缓存
                        new CachePenetration().setDataToKV(key, result);
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
        }
        return result;
    }

    private  void setDataToKV(String key, String result) {
        redisCacheTemplate.opsForValue().set(key,result);
    }

    private  String getDataByDB(String key) {
        //数据库查询逻辑
        return null;
    }

    private  String getDataByKV(String key) {
        Serializable serializable = redisCacheTemplate.opsForValue().get(key);
        if(Objects.isNull(serializable)){
            return null;
        }
        return (String)serializable;

    }

}
