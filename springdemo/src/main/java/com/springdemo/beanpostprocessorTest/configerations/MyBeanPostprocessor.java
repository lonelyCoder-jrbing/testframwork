package com.springdemo.beanpostprocessorTest.configerations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.SimpleInstantiationStrategy;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;


@Component
public class MyBeanPostprocessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessorBeforeInitialization.print bean name ........" + beanName + "=>" + bean);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postprocessotAfterInitialization..print bean name..........." + beanName + "=>" + bean);
        dosomthing();
        return bean;
    }

    public void dosomthing() {
        IntStream.range(0, 10).mapToObj(i -> new Thread(() -> System.out.println("mmm"), String.valueOf(i))).forEach(t -> {
            System.out.println("thread name:  " + t.getName());
            t.start();
        });
    }

}
