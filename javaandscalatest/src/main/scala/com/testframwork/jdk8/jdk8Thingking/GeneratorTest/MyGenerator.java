package com.testframwork.jdk8.jdk8Thingking.GeneratorTest;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyGenerator implements Supplier {
    Random r = new Random(46);
    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    @Override
    public Object get() {
        return "" + letters[r.nextInt(letters.length)];
    }

    public static void main(String[] args) {
//        List collect =(List) Stream.generate(new MyGenerator())
//                .limit(30).collect(Collectors.toList());

        String collect =(String) Stream.generate(new MyGenerator())
                .limit(30).collect(Collectors.joining());

        System.out.println(collect);

    }


}
