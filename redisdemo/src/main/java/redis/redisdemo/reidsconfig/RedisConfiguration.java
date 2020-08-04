package redis.redisdemo.reidsconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisPoolConfig;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableCaching
public class RedisConfiguration {
    @Autowired
    private RedisPoolConfigure redisPoolConfigure;
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        List<String> addr = redisPoolConfigure.getADDR();
        System.out.println("addr:  "+addr);
        System.out.println("port:  "+redisPoolConfigure.getPORT());
        List<RedisNode> nodes = addr.stream().map(el -> new RedisNode(el, Integer.valueOf(redisPoolConfigure.getPORT()).intValue())).collect(Collectors.toList());
        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(3);
        return redisClusterConfiguration;
    }
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.valueOf(redisPoolConfigure.getMAX_IDLE()));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(redisPoolConfigure.getMAX_WAIT()));
        return jedisPoolConfig;
    }

//    /**
//     * @description 创建redis连接工厂
//     */
//    public RedisConnectionFactory getConnectionFactory() {
//        System.out.println("host:" + redisPoolConfigure.getADDR());
//        System.out.println("port:" + redisPoolConfigure.getPORT());
//        RedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(
//                new JedisShardInfo(redisPoolConfigure.getADDR().get(0), redisPoolConfigure.getPORT()));
//        return jedisConnectionFactory;
//    }
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        lettuceConnectionFactory.setTimeout(Integer.valueOf(redisPoolConfigure.getTIME_OUT()));
        lettuceConnectionFactory.setPassword(redisPoolConfigure.getREDIS_PASS());
        lettuceConnectionFactory.setDatabase(0);
        lettuceConnectionFactory.setShareNativeConnection(Boolean.FALSE);
        return lettuceConnectionFactory;
    }
//    /**
//     * @description 设置缓存管理器
//     */
//    @Bean(name = "cacheManager")
//    public RedisCacheManager getRedisCacheManager() {
//        LettuceConnectionFactory lettuceConnectionFactory = lettuceConnectionFactory();
//
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheTemplate(lettuceConnectionFactory));
//        return redisCacheManager;
//    }


    @Bean
    public StringRedisTemplate stringRedisTemplate(@Qualifier("lettuceConnectionFactory") LettuceConnectionFactory connectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
       return stringRedisTemplate;
    }

    /**
     * desc:  默认情况下的模板只能支持RedisTemplate<String, String>，
     *        也就是只能存入字符串，所以自定义模板很有必要。添加配置类RedisCacheConfig.java
     * @param connectionFactory
     * @return
     */


    @Bean
    public RedisTemplate<String, Object> redisCacheTemplate(@Qualifier("lettuceConnectionFactory")LettuceConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        return template;
    }

}