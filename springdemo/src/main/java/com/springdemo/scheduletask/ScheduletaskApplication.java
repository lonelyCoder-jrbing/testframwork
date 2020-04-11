package com.springdemo.scheduletask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling    //允许进行定时任务
public class ScheduletaskApplication {

    public static void main(String[] args) {

        SpringApplication.run(ScheduletaskApplication.class, args);
    }

}
