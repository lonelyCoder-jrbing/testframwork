package com.springdemo.annotationtest.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // 什么时候使用该注解，我们定义为运行时；
@Target({ElementType.METHOD}) //用于什么地方，我们定义为作用于方法上；
@Documented //注解是否将包含在 JavaDoc 中
public @interface WebLog {
    String description() default "";
}
