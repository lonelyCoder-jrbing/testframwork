package com.example.service.impl;
import com.example.service.api.ITestNacos;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Service;

import static org.apache.dubbo.rpc.cluster.Constants.DEFAULT_LOADBALANCE;

/**
 *1.actives 消费者端，最大并发调用限制
 *2.retries：失败重试次数，默认重试 2 次
 *3.loadbalance：负载均衡算法，默认随机
 *4.timeout：方法调用超时
 */

@Service(group = "test-nacos2",
        retries = 2,
        timeout = 10000,
        loadbalance = DEFAULT_LOADBALANCE,   //默认使用的是random
        actives = 10,
        version = "1.1.0")
public class TestNacosServiceImpl implements ITestNacos {
    @HystrixCommand
    @Override
    public String getOrderFormat(Integer integer) {
        return "jurongbing01";
    }



}
