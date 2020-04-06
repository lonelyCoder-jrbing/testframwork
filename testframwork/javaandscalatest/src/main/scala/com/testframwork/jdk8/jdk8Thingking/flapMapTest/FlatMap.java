package com.testframwork.jdk8.jdk8Thingking.flapMapTest;

import java.util.Arrays;
import java.util.stream.Stream;

//从映射返回的每个流都会自动扁平为组成它的字符串
public class FlatMap {
    public static void main(String[] args) {
        Arrays.asList("1", "2", "3").stream()
                .flatMap(i -> Stream.of("jrbing", "byy", "Beaker")).forEach(e -> System.out.println(e));

        System.out.println("=================================");

        Arrays.asList("1", "2", "3").stream()
                .map(i -> Stream.of("jrbing", "byy", "Beaker")).forEach(e -> System.out.println(e.reduce((k,v)->k+"  "+v)));


    }
}
