package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.controller.castest.request.BalanceRequest;
import com.mybatisdemos.controller.castest.request.GoodRequest;
import com.mybatisdemos.controller.castest.response.BalanceResponse;
import com.mybatisdemos.controller.castest.response.GoodResponse;
import com.mybatisdemos.controller.springtransaction.request.AccountRequest;
import com.mybatisdemos.dao.balancedao.BalanceMapper;
import com.mybatisdemos.serivce.BalanceService;
import com.mybatisdemos.serivce.BalanceService1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
@Service
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private BalanceMapper balanceMapper;
     @Autowired
    private BalanceService1 balanceService1;

    @Override
    public boolean updateBalance(BalanceRequest request) {
        log.info("OrderInfoRequest==={}", request.getBalance());
        return balanceMapper.updateBlance(request);
    }

    @Override
    public boolean updateGoods(GoodRequest request) {
        return balanceMapper.updateGoods(request);
    }

    @Override
    public GoodResponse selectGoodsById(GoodRequest request) {
        return balanceMapper.selectGoodsById(request);
    }

    @Override
    public BalanceResponse getNewBlance(BalanceRequest request) {
        return balanceMapper.getNewBlance(request);
    }

    //spring事务测试代码
    //先去调用内层的，无错误，事务提交。外层的有错误，进行回退。
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean saveBalance(AccountRequest request) {
        //这里在方法调用的时候要注意，对象内部方法互相调用，
        //Transactional注解不会被拦截到,@Transactional注解们不会生效
        boolean b =balanceService1.saveBalance(request);
        boolean b1 = balanceMapper.insertAccount(request);
        int i = 1 / 0;
        return b1 && b;
    }
}
