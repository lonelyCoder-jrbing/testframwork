package com.mybatisdemos.rabbitmq.mqcallback;

import com.mybatisdemos.dao.orderdao.OrderDao;
import com.mybatisdemos.serivce.dto.ConfirmDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 消息发送到交换机确认机制
 * @author zhuzhe
 * @date 2018/5/25 15:53
 * @email 1529949535@qq.com
 */
@Slf4j
public class ORDERConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private OrderDao orderDao;
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("设置消息发送确认回调，发送成功后更新消息表状态");
        if (ack) {
            ConfirmDTO confirmDTO = new ConfirmDTO();
            log.info("correlationData===={}",correlationData);
            confirmDTO.setOrderId(correlationData.getId());
            confirmDTO.setSendStatus("1");
            confirmDTO.setCreateTime(new Date());
            orderDao.updateConfirm(confirmDTO);
        }
    }
}
