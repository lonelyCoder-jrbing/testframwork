package com.mybatisdemos.rabbitmq.sender;

import com.mybatisdemos.rabbitmq.rabbitconfig.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 消息发送  生产者1
 *
 * @author jurongbing
 * @date 
 *
 */
@Slf4j
@Component
@Order(3)
public class FirstSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param uuid
     * @param message 消息
     */
    public void send(String uuid, Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY2,
                message, correlationId);
    }

    /**
     * 发送消息
     *
     * @param uuid
     * @param message 消息
     */
    public void send2(String uuid, Object message) {
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY1,
                message, correlationId);
    }

    /****
     * 交换器类型为header的交换机没有routing key
     * @param exchaneName
     * @param message
     */
    public void send(String exchaneName, Message message) {
        rabbitTemplate.convertAndSend(exchaneName, null, message);
    }
}