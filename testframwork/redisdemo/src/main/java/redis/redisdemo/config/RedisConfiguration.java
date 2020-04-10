package redis.redisdemo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisPoolConfig;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
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
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setTimeout(Integer.valueOf(redisPoolConfigure.getTIME_OUT()));
        jedisConnectionFactory.setPassword(redisPoolConfigure.getREDIS_PASS());
        jedisConnectionFactory.setUsePool(Boolean.valueOf(redisPoolConfigure.getUSE_POOL()));
        return jedisConnectionFactory;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
       return stringRedisTemplate;
    }
}
