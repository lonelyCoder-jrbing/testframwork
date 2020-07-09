package com.springdemo.smartInitializingsingletontest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

@Component
public class DogPostProcessor implements BeanPostProcessor {

    @Autowired
    private Person person;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof Dog)) {
            return null;
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            if (method.getName().equals("makeSound")) {
                System.out.println("注意了狗狗要开始叫了!");
            }
            return method.invoke(bean, args);
        });
        Dog proxyDog = (Dog) enhancer.create();
        person.addDog(proxyDog);
        return proxyDog;
    }
}