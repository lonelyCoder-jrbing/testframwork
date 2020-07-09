package com.springdemo.beanregistrationdemo;

import com.springdemo.beanregistrationdemo.entity.Person04;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:注意一下set设值和构造方法传值的区别
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //通过给定的User类型来生成BeanDefinition
        Person04 person04= new Person04();
        person04.setAge(12);
        person04.setName("ju");
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(person04.getClass());
        //将生成的BeanDefinition注册到容器中
        registry.registerBeanDefinition("user",rootBeanDefinition);
    }


}
