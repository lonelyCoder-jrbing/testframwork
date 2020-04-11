package com.testframwork.jdk8.jdk8Thingking.HigherOrderFunction;

import java.util.Random;
import java.util.function.IntSupplier;

public class Closure4 {
    IntSupplier makeFun(final int x) {
        final int i = 0;
        return () -> x + i;
    }

    public static void main(String[] args) {
        Closure4 c = new Closure4();

        IntSupplier intSupplier = c.makeFun(3);
        int asInt = intSupplier.getAsInt();
        System.out.println(asInt);
        new Random(47).ints(5, 20)
                .limit(7).sorted().forEach(System.out::println);


    }


}