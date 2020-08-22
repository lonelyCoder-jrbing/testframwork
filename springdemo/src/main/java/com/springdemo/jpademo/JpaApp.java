package com.springdemo.jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.springdemo.jpademo")
public class JpaApp {

    public static void main(String[] args) {
        SpringApplication.run(JpaApp.class, args);
    }


}
