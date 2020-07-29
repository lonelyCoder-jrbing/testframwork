package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.controller.castest.request.BalanceRequest;
import com.mybatisdemos.controller.castest.request.GoodRequest;
import com.mybatisdemos.controller.castest.response.BalanceResponse;
import com.mybatisdemos.controller.castest.response.GoodResponse;
import com.mybatisdemos.dao.balancedao.BalanceMapper;
import com.mybatisdemos.serivce.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean updateBalance(BalanceRequest request) {
        log.info("request==={}", request.getBalance());
        return    balanceMapper.updateBlance(request);
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
}
