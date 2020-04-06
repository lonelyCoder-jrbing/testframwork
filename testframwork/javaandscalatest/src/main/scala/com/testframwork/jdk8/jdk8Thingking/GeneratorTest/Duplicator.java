package com.testframwork.jdk8.jdk8Thingking.GeneratorTest;

import java.util.stream.Stream;

public class Duplicator {
    public static void main(String[] args) {
        //generator需要的参数是suplier函数 ,该函数是无参数有返回值的.

        Stream.generate(() -> "duplicate")
                .limit(3)
                .forEach(System.out::println);
    }
}