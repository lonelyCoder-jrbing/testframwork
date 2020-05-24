package com.es.esdemo.receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.es.esdemo.requestTest.pojo.Person;
import com.rabbitmq.tools.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 消息消费者1
 *
 * @author zhuzhe
 * @date 2018/5/25 17:32
 * @email 1529949535@qq.com
 */
@Component
@Slf4j
public class FirstConsumer {
    @Autowired
    RestHighLevelClient clients;

    @RabbitListener(queues = {"first-queue", "second-queue"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        // 处理消息
        log.info("FirstConsumer {} handleMessage :" + message);
        JSONObject jsonObject = JSONObject.parseObject(message);
        Person person = JSONObject.toJavaObject(jsonObject, Person.class);


        //将拿到的数据插入到es中
        String uuid = UUID.randomUUID().toString();
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("userName", person.getUserName());
        personMap.put("age", person.getAge());
        personMap.put("gender", person.getGender());
        IndexRequest source = new IndexRequest("esdb", "table", uuid).source(personMap);
        clients.index(source, RequestOptions.DEFAULT);


    }
}
