package com.springdemo.applicationcontextInitializertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.springdemo.applicationcontextInitializertest"})
public class Ap {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Ap.class);
        // 方法一：添加自定义的 ApplicationContextInitializer 实现类的实例(注册ApplicationContextInitializer)
        springApplication.addInitializers(new ApplicationContextInitializer1());

        ConfigurableApplicationContext context = springApplication.run(args);

        context.close();
    }


}