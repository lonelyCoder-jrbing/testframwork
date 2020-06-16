package com.springdemo.beanregistrationdemo;

import com.springdemo.beanregistrationdemo.entity.Person04;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //通过给定的User类型来生成BeanDefinition
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Person04.class);
        //将生成的BeanDefinition注册到容器中
        registry.registerBeanDefinition("user",rootBeanDefinition);
    }


}
