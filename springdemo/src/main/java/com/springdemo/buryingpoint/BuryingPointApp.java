package com.springdemo.buryingpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springdemo.buryingpoint")
public class BuryingPointApp {

    public static void main(String[] args) {
        SpringApplication.run(BuryingPointApp.class, args);
    }


}
