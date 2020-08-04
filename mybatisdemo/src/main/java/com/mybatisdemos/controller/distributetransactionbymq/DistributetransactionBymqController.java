package com.mybatisdemos.controller.distributetransactionbymq;

import com.mybatisdemos.controller.distributetransactionbymq.request.OrderInfoRequest;
import com.mybatisdemos.serivce.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc:采用rabbitmq进行分布式事务的实现
 **/

@RestController
@RequestMapping("/distributetransactionBymq")
@Slf4j
public class DistributetransactionBymqController {

    //模拟用户下单和卖家发货两个操作，分别对应订单系统和运单系统
    // 假设目前的系统是订单系统，运单系统是另外一个系统

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/buyGoods")
    public String buyGoods(@RequestBody OrderInfoRequest request) {
        request.setCreateTime(new Date());
      log.info("@DistributetransactionBymqController##buyGoods={}",request);
        goodsService.createOrder(request);
        return "ok";
    }


}
