package redis.redisdemo.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.redisdemo.pojo.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/redis")
@Slf4j
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

    //redis的批量插入数据
    @GetMapping(value = "/test04")
    @ResponseBody
    public Student test04() {
        List<Student> students = new ArrayList<>();
        Student student1 = new Student(1, "jurongbing", 12);
        students.add(student1);
        Student student2 = new Student(2, "jurongbing2", 13);
        students.add(student2);
        //
        String g = "STUDENT";

        //批量插入数据 方式一
        redisTemplate.executePipelined(new RedisCallback<List<Student>>() {
            @Override
            public List<Student> doInRedis(RedisConnection connection) throws DataAccessException {
                students.forEach(el -> {
                    connection.hSet(g.getBytes(), String.valueOf(el.getId()).getBytes(), JSON.toJSONString(el).getBytes());
                });
                return null;
            }
        });
        //批量插入数据 方式二
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        Map<Integer, Student> collect = students.stream().collect(Collectors.toMap((Student::getId), Function.identity(), (k1, k2) -> k2));
        Map<byte[], byte[]> hashes = new HashMap<>();

        collect.forEach((k, v) -> {
            hashes.put(String.valueOf(k).getBytes(), v.toString().getBytes());
        });
        connection.hMSet(g.getBytes(), hashes);


        //通过游标获取
        Cursor<Map.Entry<Object, Object>> scan = redisTemplate.opsForHash().scan(g, ScanOptions.scanOptions().count(1000).build());
        while (scan.hasNext()){
            Map.Entry<Object, Object> next = scan.next();
            log.info("key:   {}",next.getKey());
            log.info("value:   {}",next.getValue());
        }
        return null;

    }

}
