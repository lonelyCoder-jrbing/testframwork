package com.mybatisdemos.controller.mq;

import com.alibaba.fastjson.JSON;
import com.mybatisdemos.sender.FirstSender;
import com.mybatisdemos.sender.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class MqController {
    @Autowired
    private FirstSender firstSender;

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


}
