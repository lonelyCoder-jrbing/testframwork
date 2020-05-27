package com.es.esdemo.config.mqcallback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 消息发送到交换机确认机制
 * @author zhuzhe
 * @date 2018/5/25 15:53
 * @email 1529949535@qq.com
 */

/****
 * 延时队列使用场景
 * 1.订单业务：在电商中，用户下单后30分钟后未付款则取消订单。
 *
 * 2.短信通知：用户下单并付款后，1分钟后发短信给用户。
 */

/*****
 * 死信队列 听上去像 消息“死”了 其实也有点这个意思，死信队列 是 当消息在一个队列 因为下列原因：
 *
 * 消息被拒绝（basic.reject/ basic.nack）并且不再重新投递 requeue=false
 * 消息超期 (rabbitmq Time-To-Live -> messageProperties.setExpiration())
 * 队列超载
 */
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("MsgSendConfirmCallBack  , 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息消费成功");
        } else {
            //可以在这里做处理消息发送失败的逻辑，私信队列也叫做延时队列
            System.out.println("消息消费失败:" + cause+"\n重新发送");
        }
    }
}
