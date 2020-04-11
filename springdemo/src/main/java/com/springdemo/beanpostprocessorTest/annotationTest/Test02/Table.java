package com.springdemo.beanpostprocessorTest.annotationTest.Test02;


import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE})
public @interface Table {

     String value() default "";
}
