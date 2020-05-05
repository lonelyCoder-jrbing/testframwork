package com.springdemo.initaillizebeanmethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = "com.springdemo.initaillizebeanmethod")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


}
