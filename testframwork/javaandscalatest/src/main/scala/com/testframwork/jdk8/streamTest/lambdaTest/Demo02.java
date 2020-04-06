package com.testframwork.jdk8.streamTest.lambdaTest;

import com.testframwork.jdk8.optionalTest.Student;
import com.testframwork.jdk8.streamTest.Test01;
import com.testframwork.jdk8.streamTest.lambdaTest.TestInterface.FunctionalInterfaceTest01;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class Demo02 {

    private static boolean getName(Student x) {
        Test01 t = new Test01();
        List<com.testframwork.jdk8.streamTest.Student> filt = t.getStudent().stream().filter(e -> e.getName().equals("第一位")).collect(Collectors.toList());

        System.out.println(filt);
        return filt.size() == 0;
    }

    @Test
    public void test01() {
//这种方法相当于是将函数式接口进行了实现
        func01(Demo02::getName);
    }

    @Test
    public void test02() {
        func01(x ->
                x.getName().equals(null));
    }


//    private void func(FunctionalInterfaceTest interfaceParam) {
//        Student x = new Student();
//        interfaceParam.getName(x);
//    }
//
    private void func01(FunctionalInterfaceTest01 interfaceTest01) {
        Student x = new Student();
        interfaceTest01.getName(x);
    }

}
