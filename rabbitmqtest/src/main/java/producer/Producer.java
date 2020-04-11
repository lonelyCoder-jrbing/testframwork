package producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import mqutil.ConnectionUtil;

public class Producer {
	  private final static String EXCHANGE_NAME = "exchange_name_test";
	  private final static String QUEUE_NAME = "queue_name_test";
	  private final static String ROUTING_KEY = "routing_key_test";
	
	  public static void main(String[] args) {
		  Producer producer = new Producer();
		  try {
			  producer.sendMessage();
		  } catch (IOException | TimeoutException e) {
			  e.printStackTrace();
		  }
	  }
	
	public void sendMessage() throws IOException, TimeoutException {
		  ConnectionUtil util = new ConnectionUtil();
		  Connection connection = util.getConnection();		
		  //创建一个信道
		  Channel channel = connection.createChannel();
		
		  /*
		   * 1.在信道中声明一个exchange
		   * 
		   * 参数1：交换器名称
		   * 参数2：交换器类型，“direct”（直接类型）、“fanout”（扇形）、“topic”（匹配类型）、“headers”
		   * 参数3：设置是否持久化，设置为true 表示持久化， 反之是非持久化。持久化可以将交换器存盘，在服务器重启的时候不会丢失相关信息
		   * 参数4：设置是否自动删除，设置为true则表示自动删除。自动删除的前提是至少有一个队列或者交换器与这个交换器绑定，之后所有与这个
		   *      交换器绑定的队列或者交换器都与此解绑。注意不能错误地把这个参数理解为:"当与此交换器连接的客户端都断开时，RabbitMQ
		   *      会自动删除本交换器"
		   * 参数5：是否内置，true表示为内置的交换器，即客户端无法直接发送消息到这个交换器，只能使用其他交换器路由到此交换器
		   * 参数6：其他参数
		   */
		  channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, false, null);
		
		  //2.在信道中生成一个queue
		  channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
		  //3.将exchange和queue绑定，绑定规则为routing key
		  channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);
		
		  String message = "This is from producer2 ! !";
		  System.out.println(" [生产者2] Start Send Message ! ! ");
		
		  //4.发送消息，路由规则同“3”中绑定的routing key
		  channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());


		  System.out.println(" [生产者2] Send Message End ! !");
		
		  this.close(connection, channel);
	}
	
	  public void close(Connection connection , Channel channel) throws IOException, TimeoutException {
		  channel.close();
		  connection.close();
	  }
}
