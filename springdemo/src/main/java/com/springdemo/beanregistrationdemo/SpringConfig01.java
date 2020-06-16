package com.springdemo.beanregistrationdemo;

import com.springdemo.applicationeventpublisheraware.entity.User;
import com.springdemo.beanregistrationdemo.entity.Person01;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
@Configuration
public class SpringConfig01 {

    //向容器中注册一个Bean
    @Bean
    public Person01 person01(){
        return new Person01("ying",30);
    }
}
