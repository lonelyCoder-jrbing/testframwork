package com.testframwork.jdk8.streamTest.lambdaTest;

import com.testframwork.jdk8.optionalTest.Student;
import com.testframwork.jdk8.streamTest.Test01;
import com.testframwork.jdk8.streamTest.lambdaTest.TestInterface.FunctionalInterfaceTest01;
import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
//        func01(Demo02::getName);
    }

    @Test
    public void test02() {
        Student student = new Student();
        student.setMan(Optional.of(Boolean.TRUE));
        boolean b = func01(i ->
                i.getMan().get() == true,student);
        System.out.println("b  "+    b);

    }


    //    private void func(FunctionalInterfaceTest interfaceParam) {
//        Student x = new Student();
//        interfaceParam.getName(x);
//    }
//
    private boolean func01(FunctionalInterfaceTest01 interfaceTest01,Student x) {
        return interfaceTest01.isMan(x);
    }

    @Test
    public void test03() {
        Test01 t = new Test01();
        List<com.testframwork.jdk8.streamTest.Student> student = t.getStudent();
        com.testframwork.jdk8.streamTest.Student student1 = new com.testframwork.jdk8.streamTest.Student();
        student1.setName("zhibo");
        student1.setNumber(null);
        student.add(student1);
        Map<String, Integer> collect = student.stream()
                .collect(Collectors.toMap(com.testframwork.jdk8.streamTest.Student::getName, com.testframwork.jdk8.streamTest.Student::getNumber));

        System.out.println(collect);


    }

    @Data
    public static class ErrResponse<T> {

        private String name;
        private String code;
        private T data;
    }


}
