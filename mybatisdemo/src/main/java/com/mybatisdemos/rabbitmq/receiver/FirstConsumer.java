package com.mybatisdemos.rabbitmq.receiver;

import com.alibaba.nacos.client.utils.JSONUtils;
import com.mybatisdemos.dao.studentinfodao.UsersMapper;
import com.mybatisdemos.domain.UserLoginPO;
import com.mybatisdemos.serivce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    private UsersMapper usersMapper;
    @Autowired
    private UserService userService;

    @RabbitListener(queues = {"first-queue", "second-queue"}, containerFactory = "rabbitListenerContainerFactory")
    @Transactional(rollbackOn = Exception.class)
    public void handleMessage(String message) throws Exception {
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :" + message);

        UserLoginPO user = (UserLoginPO) JSONUtils.deserializeObject(message, UserLoginPO.class);
        //将拿到的数据插入到es中
        log.info("将拿到的数据插入到mysql中:{}", user);
        List<UserLoginPO> userLoginPOS = new ArrayList<>();
        userLoginPOS.add(user);
        int i = userService.insertUserbatch(userLoginPOS);
        log.info("insert into msql= {}条", i);
    }
}
