package com.mybatisdemos.serivce.impl;

import com.alibaba.fastjson.JSON;
import com.mybatisdemos.controller.distributetransactionbymq.request.OrderInfoRequest;
import com.mybatisdemos.dao.orderdao.OrderDao;
import com.mybatisdemos.serivce.GoodsService;
import com.mybatisdemos.serivce.dto.ConfirmDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc:
 **/
@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService, RabbitTemplate.ConfirmCallback {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public MessagePostProcessor getMessagePostProcessor() {
        return message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setContentEncoding("utf-8");
            // 设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public void createOrder(OrderInfoRequest request) {
        boolean b = orderDao.insertOrder(request);
        ConfirmDTO confirmDTO = new ConfirmDTO();
        confirmDTO.setOrderId(request.getOrderId());
        confirmDTO.setOrderInfo(request.getOrderContent());
        confirmDTO.setSendStatus("0");
        confirmDTO.setCreateTime(new Date());
        log.info("@@@GoodsServiceImpl###createOrder##confirmDTO={}", confirmDTO);
        boolean b1 = orderDao.insertConfirm(confirmDTO);
        String msg = JSON.toJSONString(request);

        CorrelationData correlationData = new CorrelationData(request.getOrderId());

        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", msg, getMessagePostProcessor(), correlationData);
    }

    //定时扫描记录表，将发送状态为0的消息再次发送，甚至可以记录重发次数，必要时人工干预，生产环境中需要单独部署定时任务
    @Scheduled(cron = "30/30 * * * * ?")
    public void scanOrder() {
        log.info("定时扫面confirm表");
//        String sql = "select o.* from t_order o join t_confirm c on o.order_no = c.id where c.send_status = 0";
        ConfirmDTO confirmDTO = new ConfirmDTO();
        confirmDTO.setSendStatus("0");
        List<OrderInfoRequest> orderBeanList = orderDao.queryForList(confirmDTO);

        log.info("GoodsServiceImpl@@@@@@@@@@@@@@@scanOrder###############orderBeanList={}", orderBeanList);
        orderBeanList.forEach(el -> {
            String msg = JSON.toJSONString(el);
            log.info("GoodsServiceImpl@@@@@@@@@@@@@@@scanOrder-----------convertAndSend---msg={}", msg);
            CorrelationData correlationData = new CorrelationData(el.getOrderId());

            rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", msg, getMessagePostProcessor(), correlationData);

        });
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("设置消息发送确认回调，发送成功后更新消息表状态");
        if (ack) {
            String sql = "update t_confirm set send_status = ? where id = ?";
            ConfirmDTO confirmDTO = new ConfirmDTO();
            log.info("correlationData===={}", correlationData);
            confirmDTO.setOrderId(correlationData.getId());
            confirmDTO.setSendStatus("1");
            confirmDTO.setCreateTime(new Date());
            orderDao.updateConfirm(confirmDTO);
        }
    }
}
