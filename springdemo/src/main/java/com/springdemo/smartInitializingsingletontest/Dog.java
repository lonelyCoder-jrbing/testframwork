package com.springdemo.smartInitializingsingletontest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * create by sumerian on 2020/7/7
 * <p>
 * desc:
 **/
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class Dog {

    public void makeSound() {
    log.info("wang.wang..................");
    }
}
