package com.testframwork.jdk8.jdk8Thingking.peek;

import java.util.Arrays;

public class Demo01 {

    public static void main(String[] args) {

        Arrays.asList("a", "b", "c")
                .stream()
                .map(w -> w + " ")
                .peek(System.out::println)
                .map(String::toUpperCase)
                .peek(e -> System.out.println("intermedite result : " + e))
                .map(String::toLowerCase)
                .forEach(e -> System.out.println("result:  " + e));


    }


}
