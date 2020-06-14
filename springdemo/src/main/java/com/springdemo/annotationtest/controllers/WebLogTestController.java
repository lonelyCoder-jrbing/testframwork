package com.springdemo.annotationtest.controllers;

import com.springdemo.annotationtest.annotations.WebLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WebLogTestController {

    /**
     * 一般请求，不需打印日志
     * @return
     */
    @GetMapping("/sayHello")
    @WebLog(description = "sayHello")
    public String sayHello() {
        return "Hello";
    }

    /**
     * 需要打印日志的请求
     * @param str
     * @return
     */
    @GetMapping("/webLog")
    @WebLog(description = "这里是方法描述")
    public String webLogTest(String str) {
        return "成功返回:" + str;
    }
}
