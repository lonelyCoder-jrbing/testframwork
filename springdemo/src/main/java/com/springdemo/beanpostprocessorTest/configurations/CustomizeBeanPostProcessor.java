package com.springdemo.beanpostprocessorTest.configurations;


import com.springdemo.beanpostprocessorTest.service.CalculateService;
import com.springdemo.beanpostprocessorTest.utils.Utils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomizeBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("calculateService".equals(beanName)) {
            Utils.printTrack("do postProcessor before initialization...");
            CalculateService caculateservice = (CalculateService) bean;
            caculateservice.setServiceDesc("desc from " + this.getClass().getSimpleName());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("calculateService".equals(beanName)) {
            Utils.printTrack("do postProcessor after initialazation");
        }
        
        return bean;
    }
}
