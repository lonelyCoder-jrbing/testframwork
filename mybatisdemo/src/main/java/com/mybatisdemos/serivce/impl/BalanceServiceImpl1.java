package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.controller.springutransaction.request.AccountRequest;
import com.mybatisdemos.dao.balancedao.BalanceMapper;
import com.mybatisdemos.serivce.BalanceService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * create by sumerian on 2020/7/31
 * <p>
 * desc: 事务的传播特性的测试
 **/
@Service
public class BalanceServiceImpl1 implements BalanceService1 {
    @Autowired
    private BalanceMapper balanceMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean saveBalance(AccountRequest request) {
//        AccountRequest request1 = new AccountRequest();
        request.setBalance(1212.0F);
        request.setId(2);
        request.setName("lisi");
        request.setVersion(0);
        boolean b = balanceMapper.insertAccount(request);
        return b;
    }
}
