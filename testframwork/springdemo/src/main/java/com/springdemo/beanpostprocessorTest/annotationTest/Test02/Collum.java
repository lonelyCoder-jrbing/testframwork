package com.springdemo.beanpostprocessorTest.annotationTest.Test02;


import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Collum {

String value() default "";

}
