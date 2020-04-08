package redis.redisdemo.Controller;

import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RedissonClient client;

    private static final String lock = ".......";

    private static final long waitTime = 30L;

    @GetMapping(value = "/test1")
    @ResponseBody
    public String getValueBVyKey(@RequestParam String key) {
        RLock lock = client.getLock(RedisTestController.lock);
        String nameStr = "";
        try {
            lock.tryLock(waitTime, TimeUnit.SECONDS);
            RBucket<Object> bucket = client.getBucket(key, new StringCodec());
            bucket.set("jrbing", 1000L, TimeUnit.SECONDS);
            RBucket<Object> name = client.getBucket(key, new StringCodec());
            nameStr = (String) name.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return nameStr;

    }

    @GetMapping(value = "/demo")
    @ResponseBody
    public String testDemo(String key) {
        RBucket<Object> name = client.getBucket(key, new StringCodec());
        String str = (String) name.get();
        return str;
    }


}
