package com.springdemo.beanregistrationdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/

@Configuration
//@ComponentScan("com.whb")
@Import(MyImportSelector.class)
public class SpringConfig03 {
}
