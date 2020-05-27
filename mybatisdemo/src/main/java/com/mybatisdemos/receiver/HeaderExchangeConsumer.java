package com.mybatisdemos.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * create by sumerian on 2020/5/27
 * <p>
 * desc: 编写交换机类型为header的消费者
 **/
@Component
@Slf4j
public class HeaderExchangeConsumer {

//监听器监听指定的queue
    @RabbitListener(queues = "header_queue")
    public void headleMessage(Message message) throws UnsupportedEncodingException {
        MessageProperties messageProperties = message.getMessageProperties();
        String contentType = messageProperties.getContentType();
        log.info("receive-headers: message body={},contentType=={}",new String(message.getBody(),"UTF-8"),contentType);
    }



}
