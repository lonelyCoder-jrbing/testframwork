package com.springdemo.ehcachedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheApp {

    public static void main(String[] args) {
        SpringApplication.run(CacheApp.class, args);
    }


}
