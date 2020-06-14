package com.mybatisdemos.eventlistener;

import com.springdemo.applicationeventpublisheraware.entity.User;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

/**
 * create by sumerian on 2020/6/14
 * <p>
 * desc:
 **/
public class MqMesgRegisterEvent extends ApplicationEvent {
    private static final long serialVersionUID = -5481658020206295565L;
    private Object msg;
    //谁发布的这个事件，souce就是谁（对象）
    public MqMesgRegisterEvent(Object source, Object msg) {
        super(source);
        this.msg = msg;
    }
    public Object getMsg() {
        return msg;
    }
}
