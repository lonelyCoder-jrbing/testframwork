package com.springdemo.beanregistrationdemo;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/

import com.springdemo.applicationeventpublisheraware.entity.User;
import com.springdemo.beanregistrationdemo.entity.Person02;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Person02.class)
public class SpringConfig02 {
}
