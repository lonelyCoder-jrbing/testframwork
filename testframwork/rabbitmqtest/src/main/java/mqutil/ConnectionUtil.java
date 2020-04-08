package mqutil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
	public Connection getConnection() throws IOException, TimeoutException {
		  /*
		   * 创建mq连接工厂
		   */
		  ConnectionFactory factory = new ConnectionFactory();
	 	  /*
		   * 配置mq连接工厂
		   */
		  factory.setHost("localhost");
		  factory.setUsername("guest");
		  factory.setPassword("guest");
		  factory.setPort(5672);
		  /*
		   * 获取到mq连接
		   */
		  Connection connection = factory.newConnection();
		  return connection;		
	  }
}
