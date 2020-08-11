package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.controller.springtransaction.request.AccountRequest;
import com.mybatisdemos.dao.balancedao.BalanceMapper;
import com.mybatisdemos.serivce.BalanceService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * create by sumerian on 2020/7/31
 * <p>
 * desc: 事务的传播特性的测试
 **/
@Service
public class BalanceServiceImpl1 implements BalanceService1 {

    @Autowired
    private BalanceMapper balanceMapper;
    //Isolation.DEFAULT  使用数据库底层的隔离级别，数据库管理员设置什么就是什么
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.DEFAULT)
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
