package com.springdemo.demoexception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "com.springdemo.demoexception")
public class DemoexceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoexceptionApplication.class, args);
    }

}
