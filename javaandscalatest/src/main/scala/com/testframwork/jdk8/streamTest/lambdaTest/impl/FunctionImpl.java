package com.testframwork.jdk8.streamTest.lambdaTest.impl;


import com.testframwork.jdk8.optionalTest.Student;
import com.testframwork.jdk8.streamTest.lambdaTest.TestInterface.FunctionalInterfaceTest;

public class FunctionImpl implements FunctionalInterfaceTest {


    @Override
    public void getName(Student x) {
        System.out.println("hello world" + x);
    }
}
