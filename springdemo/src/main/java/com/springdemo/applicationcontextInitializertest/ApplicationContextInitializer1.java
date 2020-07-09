package com.springdemo.applicationcontextInitializertest;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

//@Order的value值越小->越早执行。注：在类上标注，不是方法上
@Order(111)
public class ApplicationContextInitializer1 implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        // 打印容器里面有多少个bean
        System.out.println("bean count=====" + applicationContext.getBeanDefinitionCount());
//        applicationContext.

        // 打印人所有 beanName
        System.out.println(applicationContext.getBeanDefinitionCount() + "个Bean的名字如下：");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

    }
}