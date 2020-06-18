package com.mybatisdemos.controller.mq;

import com.alibaba.fastjson.JSON;
import com.mybatisdemos.domain.UserLoginPO;
import com.mybatisdemos.eventlistener.MqMesgRegisterEvent;
import com.mybatisdemos.rabbitmq.sender.FirstSender;
import com.mybatisdemos.rabbitmq.sender.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * create by sumerian on 2020/5/24
 * <p>
 * desc:
 **/
@RestController
@RequestMapping("/rabbitmq")
@Slf4j
public class MqController  implements ApplicationEventPublisherAware {
    @Autowired
    private FirstSender firstSender;


    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/send")
    public void send() {
        IntStream.range(0, 100000)
                .mapToObj(el -> new Person("jurongbing" + el, el, "m"))
                .forEach(el -> {
                    String uuid = UUID.randomUUID().toString();

                    String msg = JSON.toJSONString(el);
                    log.info("send message :{}", msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    firstSender.send(uuid, msg);

                });
    }

    @GetMapping("/sendAndSink2Mysql")
    public void sendAndSink2Mysql() {
        IntStream.range(0, 1000)
                .mapToObj(el -> UserLoginPO.builder().userNickName("zhibo" + el)
                        .loginTime(new Date())
                        .userAge(20 + el)
                        .userCode(String.valueOf(new Random(2).nextInt(1000)))
                        .cipher("rongbing_" + String.valueOf(new Random(2).nextInt(1000)))
                        .userName("jurongbing_test" + el)
                        .userLoginId(UUID.randomUUID().toString())
                        .build()
                )
                .forEach(el -> {
                    String uuid = UUID.randomUUID().toString();

                    String msg = JSON.toJSONString(el);
                    log.info("send message :{}", msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    firstSender.send(uuid, msg);
                    //在此处将同步给mysql的消息发布,在这里是MqController发布的这个事件，所以source就是this代表MqController
                    applicationEventPublisher.publishEvent(new MqMesgRegisterEvent(this,msg));
                });
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
           this.applicationEventPublisher = applicationEventPublisher;
    }
}
