package com.springdemo.smartInitializingsingletontest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * create by sumerian on 2020/7/7
 * <p>
 * desc:person  在初始化的时候，从beanfactory中获取了dog的实例，并且调用了makeSound方法，
 **/
@Service
public class Person implements SmartInitializingSingleton, BeanFactoryAware {

    private BeanFactory beanFactory;
    private List<Dog> managedDogs = new LinkedList<>();



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Dog dog = beanFactory.getBean(Dog.class);
        dog.makeSound();
    }

    public void addDog(Dog dog) {
        managedDogs.add(dog);
    }
}
