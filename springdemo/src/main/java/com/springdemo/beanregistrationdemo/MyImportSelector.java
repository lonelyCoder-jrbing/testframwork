package com.springdemo.beanregistrationdemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:可以一次性注册多个bean，但是这种方式是有缺陷的,缺陷就是无法为bean设置属性
 **/
public class MyImportSelector  implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.springdemo.beanregistrationdemo.entity.Person03"};
    }
}
