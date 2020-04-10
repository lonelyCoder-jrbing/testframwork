//package redis.redisdemo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.log4j.Logger;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.data.redis.core.StringRedisTemplate;
//import redis.clients.jedis.Jedis;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//@SpringBootTest
//@Slf4j
//class RedisdemoApplicationTests {
////    public Logger log = Logger.getLogger(this.getClass());
////    @Autowired
////    StringRedisTemplate redisTemplate;
//
//
//    @Before
//    public void before() {
//
//
//    }
//
//    @Test
//    public void crudFromRedisWidthSimple() {
//
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        System.out.println(jedis);
//        System.out.println("insert value to redis~~~");
////        log.info("insert value to redis~~~");
//        jedis.set("name", "hello jedis");
//        System.out.println("get value from redis, value:" + jedis.get("name"));
////        log.info("get value from redis, value:" + jedis.get("name"));
//
////        log.info("delete key from redis~~~");
////        jedis.del("name");
//
////        log.info("get value from redis, value:" + jedis.get("name"));
//
//        jedis.close();
//    }
//
//    @Test
//    public void test03() {
//
//        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//        System.out.println("连接成功");
//        //存储数据到列表中
//        jedis.lpush("site-list", "Runoob");
//        jedis.lpush("site-list", "Google");
//        jedis.lpush("site-list", "Taobao");
//        // 获取存储的数据并输出
//        List<String> list = jedis.lrange("site-list", 0, 2);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("列表项为: " + list.get(i));
//        }
//        jedis.close();
//    }
//
//    @Test
//    public void testKeys() {
//        Jedis jedis = new Jedis("127.0.0.1", 6379);
//
//        System.out.println("jedis：" + jedis);
//        Set<String> keys = jedis.keys("*");
//        Iterator<String> iterator = keys.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            System.out.println("next: " + next);
//        }
//    }
//
//    @Test
//    public void testredis(){
////        //连接本地的 Redis 服务
////        Jedis jedis = new Jedis("127.0.0.1", 6379);
////        System.out.println("连接成功");
////        redisTemplate.opsForValue().set("name","jurongbing");
////        String name = redisTemplate.opsForValue().get("name");
////        log.info("name",name);
////        jedis.close();
//    }
//
//
//
//}
