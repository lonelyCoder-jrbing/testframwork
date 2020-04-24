package com.testframwork.jdk8.jdk8Thingking.MethodReferences;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Demo02 {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(Demo02::go).start();

        }
        List<String> strings = Arrays.asList("1", "2", "3", "4");
        //返回值的类型和参数的类型不是一致的。
        Function<String, String> function = (x) -> x + "";
        //返回值的类型和参数的类型是一致的..单个参数
        UnaryOperator<String> operator = (x) -> x;
        //返回值是两个参数
        String jring = operator.apply("jring");
        System.out.println(jring);
        //无参数,返回值任意
        Supplier<List<String>> supplier = () -> strings;
        List<String> strings1 = supplier.get();
        Consumer<String> consumer = x -> x.split("\\$");
        consumer.accept("");
        Comparator<String> comparator = (x, y) -> -1;


        BinaryOperator<String> binaryOperator = (x, y) -> x + y;
        String binaryOperatorTest = binaryOperator.apply("jrbing", "byy");
        System.out.println("binaryOperatorTest:   ");
        System.out.println(binaryOperatorTest);


        operator.apply("jrbing");


    }

    void callThreadMethod() {

        Demo02 demo02 = new Demo02();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        new Thread(demo02::soSomthing).start();
    }

    void soSomthing() {


        System.out.println("let's do something....");
    }

    static void go() {
        System.out.println("let's go............");

    }


}
