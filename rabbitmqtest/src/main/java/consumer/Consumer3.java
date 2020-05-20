package consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * create by sumerian on 2020/5/15
 * <p>
 * desc:
 **/
@Component
@Slf4j
public class Consumer3 {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "", durable = "true"),
            exchange = @Exchange(value ="", type = ""),
            key = ""

    ))
    public void handleMessage(Message message, Channel channel) throws IOException {
        //确认一条消息被成功传递之后，该条消息将会被丢弃
       channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
      //当multiple参数被设为true。RabbitMQ将会确认所有传递标签小于给定数值的消息。比如通道Ch上有未确认消息，它们的传递标签是5,6,7,8，如果有确认带的传递标签是8，且multiple参数被设为true，则5-8消息都会被确认；如果multiple参数被设为false，则5,6,7消息仍未被确认。示例如下：
        byte[] body = message.getBody();
        String msg = new String(body,"UTF-8");






    }


}
