package com.mybatisdemos.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.util.Objects;

/**
 * create by sumerian on 2020/6/19
 * <p>
 * desc:kafka生产者的定义
 **/
@Component
@Slf4j
public class kafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void send(String message){
        if (Objects.nonNull(message)){
            ProducerRecord producerRecord = new ProducerRecord("hello","name","jurongbing");
            ListenableFuture<SendResult<String,Object>> send = kafkaTemplate.send(producerRecord);
              send.addCallback(new ListenableFutureCallback<SendResult<String,Object>>() {
                  @Override
                  public void onFailure(Throwable ex) {
                      //对发送失败的消息进行处理
                      log.info("hello" + " - 生产者 发送消息失败：" + ex.getMessage());
                  }
                  @Override
                  public void onSuccess(SendResult<String,Object> result) {
                      log.info("topic@ hello "+"send msg success"+result.toString());
                  }
              });
        }
    }

}
