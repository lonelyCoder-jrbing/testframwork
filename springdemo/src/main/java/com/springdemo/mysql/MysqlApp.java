package com.springdemo.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springdemo.mysql")
public class MysqlApp {

    public static void main(String[] args) {
        SpringApplication.run(MysqlApp.class, args);
    }


}
