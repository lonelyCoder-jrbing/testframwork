package com.mybatisdemos.rabbitmq.receiver;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * create by sumerian on 2020/5/26
 * <p>
 * desc:
 **/
@Component
@Slf4j
public class DLListener {


//    @RabbitListener(queues = "REDIRECT_QUEUE")
//    @RabbitHandler
//    public void redirect(Message message, Channel channel) throws IOException {
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        log.info("dead message :" + new String(message.getBody()));
//    }

    /***
     *  产生死信队列的原因:
     *     1. 消息被拒绝（basic.reject或basic.nack）并且requeue=false.
     *     2. 消息TTL过期
     *     3. 队列达到最大长度（队列满了，无法再添加数据到mq中）
     * @param message
     * @param channel
     * @throws IOException
     * @throws InterruptedException
     */
//    @RabbitListener(queues = "DL_QUEUE1")
//    @RabbitHandler
//    public void listenQueue(Message message, Channel channel) throws IOException, InterruptedException {
//
//        log.info("接收到消息==={}", message);
//        try {
////            int i = 1 / 0;
//        } catch (Exception e) {
//            //消息会进入到死信队列中被消费
//            log.error("listenQueue error: e={}", e);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//        }
//        //消息被正常消费者消费
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }


}
