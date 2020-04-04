package com.springdemo.ioctest.beanpostprocessorTest.annotationTest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * @author xyd
 * @version V1.0
 * @Package com.demo.common
 * @Description:
 * @date 2018/8/7 14:32
 */
@RestController
@RequestMapping("/hello")
public class AnnotationTestController {

    @Intercept(value = BusinessConstant.LOGIN_MANAGER_SESSION_KEY)
    @GetMapping("/get")
    public String get() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> aClass = Class.forName("com.org.spring.ioctest.beanpostprocessorTest.annotationTest.AnnotationTestController");
        Method get = aClass.getDeclaredMethod("get");
        Intercept annotation = get.getAnnotation(Intercept.class);
        String value = annotation.value();
        System.out.println(value);
        return value;
    }

    @Intercept
    @GetMapping("/login")
    public String login(HttpSession session) {
        String value = "普通用户权限";
        session.setAttribute(BusinessConstant.LOGIN_MANAGER_SESSION_KEY, "LOGIN_MANAGER_SESSION_KEY");
        return value;
    }
}
