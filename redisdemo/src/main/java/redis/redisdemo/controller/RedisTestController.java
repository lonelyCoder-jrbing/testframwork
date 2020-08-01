package redis.redisdemo.controller;

import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.redisdemo.pojo.Student;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/redis")
public class RedisTestController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisCacheTemplate;

    @Autowired
    private RedissonClient client;


    private static final String lock = ".......";

    private static final long waitTime = 30L;

    @GetMapping(value = "/test1")
    @ResponseBody
    public String getValueBVyKey(@RequestParam String key) {
        RLock lock = client.getLock(RedisTestController.lock);

//        RList<Object> listname = client.getList("listname");
//        RBitSet bitset = client.getBitSet("bitset");
        RMap<Object, Object> redisMap = client.getMap("redisMap");
        redisMap.put("name", "jurongbing");
        redisMap.put("age", 12);
        String nameStr = "";
        try {
            lock.tryLock(waitTime, TimeUnit.SECONDS);
            RBucket<Object> bucket = client.getBucket(key, new StringCodec());
            bucket.set("jrbing", 1000L, TimeUnit.SECONDS);
//            RBucket<Object> name = client.getBucket(key, new StringCodec());
            nameStr = (String) bucket.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return nameStr + redisMap.get("name");
    }

    @GetMapping(value = "/demo")
    @ResponseBody
    public String testDemo(String key) {
        RBucket<Object> name = client.getBucket(key, new StringCodec());
        String str = (String) name.get();
        return str;
    }

    @GetMapping(value = "test02")
    @ResponseBody
    public Student test02() {
        //只能存储键值对，如果key重复将会对该key进行覆盖
        redisTemplate.opsForValue().set("name", "jurongbing");
        redisTemplate.opsForValue().set("name", "boyingyue");
        redisTemplate.opsForValue().set("age", "25");

        //也是键值对，且在键重复的情况之下，会导致覆盖
        BoundHashOperations<String, Object, Object> hash = redisTemplate.boundHashOps("hash");
        redisCacheTemplate.opsForValue().set("stu", new Student(1, redisTemplate.opsForValue().get("name"), 12));
        hash.put("username", "jurongbing");
        hash.put("password", "123456");
        hash.put("username", "byy");

        Object stu = redisCacheTemplate.opsForValue().get("stu");

        return (Student) redisCacheTemplate.opsForValue().get("stu");


    }

    @GetMapping(value = "test03")
    @ResponseBody
    public Student test03() {
        //存储序列化对象
        BoundHashOperations<String, Object, Object> hash = redisTemplate.boundHashOps("hash");
        redisCacheTemplate.opsForValue().set("stu", new Student(1, "jurongbing", 12));
        Object stu = redisCacheTemplate.opsForValue().get("stu");
        return (Student) redisCacheTemplate.opsForValue().get("stu");

    }
}
