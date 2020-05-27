package com.mybatisdemos.controller.mq;

import com.mybatisdemos.sender.FirstSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by sumerian on 2020/5/27
 * <p>
 * desc:
 **/
@RestController
@Slf4j
@RequestMapping("/header")
public class HeaderExchangeTestController {

    @Autowired
    private FirstSender firstSender;

    @GetMapping("no")
    public void testMatchNo() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);//不持久化
        messageProperties.setContentType("UTF-8");
        messageProperties.setHeader("one","A");
        Message message = new Message("hello rabbitmq test match no".getBytes(),messageProperties);
        firstSender.send("headerExchange",message);
    }


}
