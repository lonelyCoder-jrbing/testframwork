package com.mybatisdemos.serivce;

import com.mybatisdemos.controller.springtransaction.request.AccountRequest;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
public interface BalanceService1 {


    boolean saveBalance(AccountRequest request);

}
