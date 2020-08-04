package com.mybatisdemos.rabbitmq.rabbitconfig;


import com.mybatisdemos.rabbitmq.mqcallback.MsgSendConfirmCallBack;
import com.mybatisdemos.rabbitmq.mqcallback.ORDERConfirmCallBack;
import com.mybatisdemos.serivce.GoodsService;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


/**
 * create by sumerian on 2020/5/23
 * <p>
 * desc:
 **/
@Configuration
@Order(2)
public class RabbitMqConfig {
    /**
     * 消息交换机的名字
     */
    public static final String EXCHANGE = "exchangeTest";
    /**
     * 队列key1
     */
    public static final String ROUTINGKEY1 = "queue_one_key1";
    /**
     * 队列key2
     */
    public static final String ROUTINGKEY2 = "queue_one_key2";

    @Autowired(required = false)
    private QueueConfig queueConfig;
    @Autowired(required = false)
    private ExchangeConfig exchangeConfig;

    @Autowired(required = false)
    private ConnectionFactory connectionFactory;



    //将消息和交换机进行绑定
    @Bean
    public Binding binding_one() {
        return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTINGKEY1);
    }

    //将消息和交换机进行绑定
    @Bean
    public Binding binding_two() {
        return BindingBuilder.bind(queueConfig.secondQueue()).to(exchangeConfig.directExchange()).with(RabbitMqConfig.ROUTINGKEY2);
    }


    /**
     * queue listener  观察 监听模式
     * 当有消息到达时会通知监听在对应的队列上的监听对象
     *
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer_one() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);

        simpleMessageListenerContainer.addQueues(queueConfig.firstQueue());
        simpleMessageListenerContainer.addQueues(queueConfig.secondQueue());
        simpleMessageListenerContainer.setExposeListenerChannel(true);
        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
        simpleMessageListenerContainer.setConcurrentConsumers(2);
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return simpleMessageListenerContainer;
    }

    /*****
     * 定义rabbitmqtemplate用于消息的发送和接受
     *
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        /**若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         */
        template.setConfirmCallback(orderConfirmCallBack());
//        template.setReturnCallback(msgSendReturnCallback());
        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         */
        //  template.setMandatory(true);
        return template;

    }
    /**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     * @return
     */
    @Bean
    public MsgSendConfirmCallBack msgSendConfirmCallBack(){
        return new MsgSendConfirmCallBack();
    }

    @Bean
    public ORDERConfirmCallBack orderConfirmCallBack(){
        return new ORDERConfirmCallBack();
    }

}
