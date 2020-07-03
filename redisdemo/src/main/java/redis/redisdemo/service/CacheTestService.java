package redis.redisdemo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import redis.redisdemo.pojo.User;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * create by sumerian on 2020/7/3
 * <p>
 * desc: springcache 操作redis的缓存切面
 **/

@Component
@EnableCaching
public class CacheTestService {

    private Map<Long, User> userMap;

    @PostConstruct
    public void init() {
        //模拟数据库
        userMap = new HashMap<Long, User>();
        userMap.put(1L, new User(1,"micro1"));
        userMap.put(2L, new User(2, "micro2"));
        userMap.put(3L, new User(3, "micro3"));
        userMap.put(4L, new User(4, "micro4"));
    }

    //注释详解见下个方法注释
    @Cacheable("user")  // 注解key属性可以执行缓存对象user(可以理解为一个map)的key
    public User getUser(Long userId) {
        System.out.println("查询数据库:userId ->" + userId);
        return userMap.get(userId);
    }
/////@Cacheable(value=“nameCache”)，这个注释的意思是，当调用这个方法的时候，会从一个名叫 nameCache 的缓存(缓存本质是一个map)中查询key为name的值，如果不存在，则执行实际的方法（即查询数据库等服务逻辑），并将执行的结果存入缓存中，否则返回缓存中的对象。这里的缓存中的 key 就是参数 name，value 就是 返回的User 对象

    @Cacheable(value = "nameCache", key = "#name")
    public User getUserByName(Long userId, String name) {
        System.out.println("查询数据库:userId ->" + userId);
        return userMap.get(userId);
    }

    @Cacheable("nameCache")
    public User getUserByName(String name) {
        System.out.println("查询数据库:userName : " + name);
        for (Long k : userMap.keySet()) {
            if (userMap.get(k).equals(name)) {
                return userMap.get(k);
            }
        }
        return null;
    }

    @CachePut("user") // 与Cacheable区别就是Cacheable先看缓存如果有，直接缓存换回，CachePut则是每次都会调用并且把返回值放到缓存
    public User getUser2(Long userId) {
        System.out.println("查询数据库:userId : " + userId);
        return userMap.get(userId);
    }

    @CacheEvict("user")
    public void removeFromCache(Long userId) {
        return ;
    }


}
