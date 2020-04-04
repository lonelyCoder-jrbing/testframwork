package com.springdemo.ioctest.beanpostprocessorTest.annotationTest;
import java.lang.annotation.*;
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Intercept {
    String value() default "guest";    //默认是游客
}
