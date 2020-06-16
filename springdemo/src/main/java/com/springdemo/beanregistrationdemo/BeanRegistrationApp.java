package com.springdemo.beanregistrationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springdemo.beanregistrationdemo")
public class BeanRegistrationApp {

    public static void main(String[] args) {
        SpringApplication.run(BeanRegistrationApp.class, args);
    }

}
