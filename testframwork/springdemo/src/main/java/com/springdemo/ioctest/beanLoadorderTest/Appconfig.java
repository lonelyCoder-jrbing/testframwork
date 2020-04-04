package com.springdemo.ioctest.beanLoadorderTest;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.springdemo.springdemo.ioctest.beanLoadorderTest")
public class Appconfig {
    //多次运行,可以看出man总是在 person之前进行加载的,如果两个bean之间互相之间有依赖的话,
    // 可以通过depends on来控制加载顺序.
    @Bean(initMethod = "say")
    @DependsOn("man")
    public Person personBean() {
        return new Person();
    }

    @Bean(name = "man", initMethod = "say")
    //@Lazy
    public Man manBean() {
        return new Man();
    }

    public static void main(String[] strings) {
        new AnnotationConfigApplicationContext(Appconfig.class);
    }

}
