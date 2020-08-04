package com.mybatisdemos.serivce;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc:
 **/
public interface TransportOrder {


    boolean createTransportOrder(Message message, Channel channel) throws IOException;

    boolean redirectDLL(Message message, Channel channel) throws IOException;


}
