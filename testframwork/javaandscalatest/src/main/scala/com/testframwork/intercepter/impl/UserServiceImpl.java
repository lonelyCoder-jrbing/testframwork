package com.testframwork.intercepter.impl;


import com.testframwork.intercepter.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public void sayHelloworld() {
        System.out.println("hello world!");
    }
}
