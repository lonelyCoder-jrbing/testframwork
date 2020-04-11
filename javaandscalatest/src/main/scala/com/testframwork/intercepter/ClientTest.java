package com.testframwork.intercepter;


import com.testframwork.intercepter.impl.InterceptorJdkProxy;
import com.testframwork.intercepter.impl.UserServiceImpl;

public class ClientTest {

    public static void main(String[] args) {
        UserService userServiceProxy = (UserService) InterceptorJdkProxy
                .bind(new UserServiceImpl(), "com.testframwork.intercepter.impl.InterceptorImpl");
        userServiceProxy.sayHelloworld();
    }
}
