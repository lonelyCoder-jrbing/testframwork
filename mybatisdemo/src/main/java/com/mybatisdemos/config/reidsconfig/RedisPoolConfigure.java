package com.mybatisdemos.config.reidsconfig;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
@RefreshScope
public class RedisPoolConfigure {

    //Redis服务器IP
    @Value("${ADDR:localhost}")
    private List<String> ADDR ;
    
    //Redis的端口号
	@Value(value = "${spring.redis.port:6379}")
	private String PORT ;
    
    //可用连接实例的最大数目
    @Value("${MAX_ACTIVE:100}")
	private  String MAX_ACTIVE ;
    
    //pool中的idle jedis实例数
	@Value("${MAX_IDLE:100}")
    private  String MAX_IDLE ;

	@Value("${USE_POOL:true}")
	private String USE_POOL;
    //等待可用连接的最大时间，单位毫秒
	@Value("${MAX_WAIT:1000}")
    private  String MAX_WAIT ;
    //超时时间，单位毫秒
	@Value("${TIME_OUT:3000}")
    private  String TIME_OUT ;
    //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
	@Value("${EVICTION_POLICY_CLASS_NAME:DefaultEvictionPolicy}")
    private String EVICTION_POLICY_CLASS_NAME ;
    
    //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时
	@Value("${BLOCK_WHEN_EXHAUSTED:false}")
    private String BLOCK_WHEN_EXHAUSTED;
    
    //是否启用pool的jmx管理功能, 默认true
	@Value("${JMX_ENABLED:true}")
    private String JMX_ENABLED;
    
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	@Value("${TEST_ON_BORROW:true}")
    private String TEST_ON_BORROW ;
    
    //服务器密码
    private String REDIS_PASS;
    //redis选择数据库DB
    private String REDIS_DB;
    
    
    private String LUASHA;
    
    private Map<String, String> configure = null;

	/**
	 * 判断传入的数据是否为纯数字构成
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		if(str==null || "".equals(str)){
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}


}
