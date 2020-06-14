package com.mybatisdemos.eventlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * create by sumerian on 2020/6/14
 * <p>
 * desc:
 **/
@Slf4j
@Component
public class EsEventListener implements ApplicationListener<MqMesgRegisterEvent> {


    @Override
    public void onApplicationEvent(MqMesgRegisterEvent mqMesgRegisterEvent) {
            log.info("mq发送过来的消息：  {}",mqMesgRegisterEvent.getMsg());
        log.info("mq发送过来的消息：  {}",mqMesgRegisterEvent.getSource());
    }
}
