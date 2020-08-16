package com.mybatisdemos.serivce.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mybatisdemos.controller.distributetransactionbymq.request.OrderInfoRequest;
import com.mybatisdemos.serivce.TransportOrder;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc:  运单业务
 **/
@Service
@Slf4j
public class TransportOrderImpl implements TransportOrder {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /***
     * 正常业务逻辑，出现消息被拒绝，ttl过期，队列达到最大长度时候，消息会被转发到死信队列中
     * @param message
     * @param channel
     * @return
     */
    @RabbitListener(queues = "DL_QUEUE")
    @RabbitHandler
    @Override
    public boolean createTransportOrder(Message message, Channel channel) throws IOException {
        //生成运单

        try {
            OrderInfoRequest orderInfoRequest = JSONObject.parseObject(message.getBody(), OrderInfoRequest.class);

            log.info("TransportOrderImpl@@createTransportOrder###orderInfoRequestStr=={}", orderInfoRequest);
            //接受的消息使用redis保证消息的幂等性
            String orderId = redisTemplate.opsForValue().get(orderInfoRequest.getOrderId());
            log.info("TransportOrderImpl@@createTransportOrder###orderId={}", orderId);
            if (orderId == null) {
                log.info("生成运单逻辑。。。。。{}", message);
                //生成运单逻辑异常
                // int i = 1 / 0;
                redisTemplate.opsForValue().set(orderInfoRequest.getOrderId(), "1");
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.info("TransportOrderImpl@@createTransportOrder###====error{}", e);
            //重新入队一次
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
        return false;
    }


    /***
     * 死信队列   为了解决消息消费端出现异常情况，而作的补偿逻辑
     * @param message
     * @param channel
     * @return
     */
    @RabbitListener(queues = "REDIRECT_QUEUE")
    @RabbitHandler
    @Override
    public boolean redirectDLL(Message message, Channel channel) throws IOException {
        //补单逻辑
        log.info("补单逻辑......{}", message);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        return true;
    }
}
