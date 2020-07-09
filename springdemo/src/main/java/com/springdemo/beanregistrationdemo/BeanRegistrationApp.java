package com.springdemo.beanregistrationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:测试向容器中注册bean的几种方式
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.springdemo.beanregistrationdemo")
public class BeanRegistrationApp {

    public static void main(String[] args) {
        SpringApplication.run(BeanRegistrationApp.class, args);
    }

}
