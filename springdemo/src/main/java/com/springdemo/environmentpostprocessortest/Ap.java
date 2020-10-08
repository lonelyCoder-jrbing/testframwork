package com.springdemo.environmentpostprocessortest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.springdemo.environmentpostprocessortest"})
public class Ap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Ap.class, args);
        String userName = context.getEnvironment().getProperty("jdbc.root.User");
        System.out.println("userName： "+userName);
        String passWord = context.getEnvironment().getProperty("jdbc.root.password");
        System.out.println("passWord: "+passWord);


    }


}