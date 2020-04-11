package redis.redisdemo.Controller;
import org.redisson.api.*;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping(value = "/redis")
public class RedisTestController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient client;

    private static final String lock = ".......";

    private static final long waitTime = 30L;

    @GetMapping(value = "/test1")
    @ResponseBody
    public String getValueBVyKey(@RequestParam String key) {
        RLock lock = client.getLock(RedisTestController.lock);
        RList<Object> listname = client.getList("listname");
        RBitSet bitset = client.getBitSet("bitset");
        RMap<Object, Object> redisMap = client.getMap("redisMap");
        redisMap.put("name","jurongbing");
        redisMap.put("age",12);

        listname.set(0,"jurongbing");
        String nameStr = "";
        try {
            lock.tryLock(waitTime, TimeUnit.SECONDS);
            RBucket<Object> bucket = client.getBucket(key, new StringCodec());
            bucket.set("jrbing", 1000L, TimeUnit.SECONDS);
            RBucket<Object> name = client.getBucket(key, new StringCodec());
            nameStr = (String) name.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return nameStr+listname.get(0);
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
    public String test01() {
        redisTemplate.opsForValue().set("name", "jurongbing");
        return redisTemplate.opsForValue().get("name");
    }
}
