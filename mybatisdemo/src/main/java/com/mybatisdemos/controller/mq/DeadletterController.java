package com.mybatisdemos.controller.mq;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * create by sumerian on 2020/5/26
 * <p>
 * desc:  死信队列测试接口
 **/
@RestController
@RequestMapping("/deadletter")
public class DeadletterController {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("/dead")
    public boolean send(String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setContentEncoding("utf-8");
            // 设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", msg, messagePostProcessor, correlationData);
        return true;
    }


}
