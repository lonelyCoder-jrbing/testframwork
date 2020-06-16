package com.springdemo.beanregistrationdemo;

import com.springdemo.beanregistrationdemo.entity.Person05;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
@Component
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Person05("jurongbing",25);
    }

    @Override
    public Class<?> getObjectType() {
        return Person05.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
