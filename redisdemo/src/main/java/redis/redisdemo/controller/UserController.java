package redis.redisdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.redisdemo.cachetroubledeal.CachePenetration;
import redis.redisdemo.pojo.User;
import redis.redisdemo.service.UserService;

import java.io.Serializable;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;
    @Autowired
    private CachePenetration cachePenetration;


    @RequestMapping("/test")
    public void test() {
        redisCacheTemplate.opsForValue().set("userkey", new User(1, "张三", 25));
        User user = (User) redisCacheTemplate.opsForValue().get("userkey");
        log.info("当前获取对象：{}", user.toString());
    }


    @Autowired
    private UserService userService;

    @RequestMapping("/test01")
    public void test01() {
        redisCacheTemplate.opsForValue().set("userkey", new User(1, "张三", 25));
        User user = (User) redisCacheTemplate.opsForValue().get("userkey");
        log.info("当前获取对象：{}", user.toString());
    }

    /****
     * spring cache具备很好的灵活性，不仅能够使用SPEL(spring expression language)
     * 来定义缓存的key和各种condition，还提供了开箱即用的缓存临时存储方案，
     * 也支持和主流的专业缓存如EhCache、Redis、Guava的集成。
     *
     */

    @RequestMapping("/add")
    public void add() {
        User user = userService.save(new User(4, "李现", 30));
        log.info("添加的用户信息：{}",user.toString());
    }

    @RequestMapping("/delete")
    public void delete() {
        userService.delete(4);
    }

    @RequestMapping("/get/{id}")
    public void get(@PathVariable("id") String idStr) throws Exception{
        if (StringUtils.isBlank(idStr)) {
            throw new Exception("id为空");
        }
        Integer id = Integer.parseInt(idStr);
        User user = userService.get(id);
        log.info("获取的用户信息：{}",user.toString());
    }

    /**
     * desc： 测试缓存穿透和雪崩
     * @param idStr
     * @throws Exception
     */
    @RequestMapping("/testCachePenetration/{id}")
    public void testCachePenetration(@PathVariable("id") String idStr) throws Exception{
        if (StringUtils.isBlank(idStr)) {
            throw new Exception("id为空");
        }
        Integer id = Integer.parseInt(idStr);
        String result =cachePenetration.getData(idStr);
        log.info("获取的用户信息：{}",result);
    }

}