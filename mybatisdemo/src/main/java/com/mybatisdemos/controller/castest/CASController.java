package com.mybatisdemos.controller.castest;

import com.mybatisdemos.controller.castest.request.BalanceRequest;
import com.mybatisdemos.controller.castest.request.GoodRequest;
import com.mybatisdemos.controller.castest.response.BalanceResponse;
import com.mybatisdemos.controller.castest.response.GoodResponse;
import com.mybatisdemos.serivce.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:1.使用数据库的乐观锁更新账户余额,商品库存
 *      2.也可以使用数据库的乐观锁实现分布式锁
 **/

@RestController
@RequestMapping(value = "/cas")
@Slf4j
public class CASController {
    @Autowired
    private BalanceService balanceService;


    //更新版本号码的方式
    @PostMapping(value = "/test01")
    public BalanceResponse updateBlance(@RequestBody BalanceRequest request) {
         balanceService.updateBalance(request);
       return balanceService.getNewBlance(request);

    }
    //扣减库存的操作
    @PostMapping(value = "/test02")
    public GoodResponse updateGoods(@RequestBody GoodRequest request) {
        balanceService.updateGoods(request);
        return balanceService.selectGoodsById(request);
    }

}
