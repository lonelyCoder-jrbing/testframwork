package com.mybatisdemos.receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.utils.JSONUtils;
import com.mybatisdemos.dao.studentinfodao.UsersMapper;
import com.mybatisdemos.domain.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
/**
 * 消息消费者1
 * @author zhuzhe
 * @date 2018/5/25 17:32
 * @email 1529949535@qq.com
 */
@Component
public class FirstConsumer {
    @Autowired
    private UsersMapper usersMapper;
    @RabbitListener(queues = {"first-queue","second-queue"}, containerFactory = "rabbitListenerContainerFactory")
    public void handleMessage(String message) throws Exception {
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+message);

        User user =(User) JSONUtils.deserializeObject(message, User.class);
        //将拿到的数据插入到es中



    }
}
