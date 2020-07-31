package com.mybatisdemos.controller.springutransaction;

import com.mybatisdemos.controller.springutransaction.request.AccountRequest;
import com.mybatisdemos.serivce.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by sumerian on 2020/7/31
 * <p>
 * desc:  测试spring中的事务传播行为
 **/
@RestController
@RequestMapping(value = "/spring")
@Slf4j
public class springtransactionController {

    @Autowired
    private BalanceService balanceService;
    @PostMapping("transaction")
    public boolean test01(@RequestBody  AccountRequest request) {
       return   balanceService.saveBalance(request);

    }


}
