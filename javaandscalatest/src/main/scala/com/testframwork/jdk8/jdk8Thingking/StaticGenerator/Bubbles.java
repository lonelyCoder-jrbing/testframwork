package com.testframwork.jdk8.jdk8Thingking.StaticGenerator;

import java.util.stream.Stream;

public class Bubbles {

    //bubbler方法与Supplier是接口兼容的,我们可以将其方法引用直接传递给stream.generate()
    public static void main(String[] args) {
        Stream.generate(Bubble::bubbler)
                .limit(5)
                .forEach(System.out::println);
    }
}