package com.testframwork.jdk8.streamTest.lambdaTest.reduceTest;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class Demo03 {

    @Test
    public void test01() {
        Optional<Integer> reduce = Stream.of(1, 2, 3, 4).reduce((x, y) -> {
            System.out.println("x" + x);
            System.out.println("y" + y);
            x += y;
            System.out.println("y" + y);
            System.out.println("x" + x);
            return x;
        });
        System.out.println("reduce" + (reduce.get()));
        System.out.println("---------------------------------");
        Optional<Integer> reduce1 = Stream.of(1, 2, 3, 4).reduce((x, y) -> x + y);
        System.out.println(reduce1.get());

    }


}
