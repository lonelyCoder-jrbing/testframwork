package com.springdemo.smartInitializingsingletontest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * create by sumerian on 2020/6/21
 * <p>
 * desc: 实现了SmartInitializingSingleton的bean会自动调用afterSingletonsInstantiated（）方法的。
 * 主要应用场合就是在所有单例 bean 创建完成之后，可以在该回调中做一些事情
 **/
@Component
@Slf4j
public class MySmartInitializingSingleton implements SmartInitializingSingleton {

    @Autowired
    DefaultListableBeanFactory defaultListableBeanFactory;


    @Override
    public void afterSingletonsInstantiated() {
        log.info("i am OK.........");
        Iterator<String> beanNamesIterator = defaultListableBeanFactory.getBeanNamesIterator();
         while(beanNamesIterator.hasNext()){
             log.info("MySmartInitializingSingleton...beanname:    {}",beanNamesIterator.next());
         }
    }
}
