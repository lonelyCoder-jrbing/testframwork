package com.testframwork.jdk8.jdk8Thingking.flapMapTest;

import java.util.Arrays;
import java.util.List;

public class FlatMapTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("张三", "里斯", "王五");
        //flatMap   中需要的参数是Function<? extends Stream>
        Arrays.asList("1", "2", "3").stream().flatMap(i -> names.stream()).forEach(System.out::println);
        //map
        System.out.println("=============mapTest================");
        Arrays.asList("1","2","3").stream().map(i->names).forEach(System.out::println);

    }
}
