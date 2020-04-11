package com.test.demoprovider.service;


import com.alibaba.dubbo.config.annotation.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String test(String param) {
        return "hello " + param;
    }
}
