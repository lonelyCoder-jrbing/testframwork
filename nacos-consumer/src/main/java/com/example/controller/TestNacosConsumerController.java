package com.example.controller;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.example.service.DemoService;
import com.example.service.MockService;
import com.example.service.api.ITestNacos;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.ServiceBean;
import org.apache.dubbo.registry.integration.RegistryDirectory;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
@RequestMapping("/consumer")
@NacosPropertySource(dataId = "nacos-consumer.yml", groupId = "test-nacos", autoRefreshed = true)
public class TestNacosConsumerController {
    @Value("${service.name:}")
    private String serviceName;
    @Autowired
    private DemoService demoService;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getCounsumerTest() throws ExecutionException, InterruptedException {

        return demoService.getOrderFormat();

    }

}
