package com.mybatisdemos.controller.mq;

import com.mybatisdemos.kafka.kafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by sumerian on 2020/6/7
 * <p>
 * desc: kafka发送 controller
 **/
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private kafkaProducer kafkaProducer;

    @RequestMapping("/message/send")
    public String send(String msg) {
        kafkaTemplate.send("hello","name", msg); //使用kafka模板发送信息
        kafkaProducer.send(msg);
        return "success";
    }
}
