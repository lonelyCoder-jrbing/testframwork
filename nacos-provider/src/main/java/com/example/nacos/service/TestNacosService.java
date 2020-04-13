package com.example.nacos.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service(group = "test-nacos2", retries = 0, timeout = 10000)
public class TestNacosService implements ITestNacos {

    @Override
    public String getOrderFormat(Integer integer) {
       return "test001";
    }
}
