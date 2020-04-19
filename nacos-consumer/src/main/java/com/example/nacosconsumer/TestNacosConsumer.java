package com.example.nacosconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.nacos.service.ITestNacos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("consumer")
public class TestNacosConsumer {

    @Reference(group = "test-nacos2", check = false)
    ITestNacos iTestNacos;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public String getCounsumerTest(){
        return iTestNacos.getOrderFormat(1);
    }
}
