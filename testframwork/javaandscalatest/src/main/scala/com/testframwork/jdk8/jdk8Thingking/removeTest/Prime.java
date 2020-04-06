package com.testframwork.jdk8.jdk8Thingking.removeTest;

import java.util.stream.LongStream;

import static java.util.stream.LongStream.iterate;
import static java.util.stream.LongStream.rangeClosed;

public class Prime {
    public static Boolean isPrime(long n) {
        return rangeClosed(2, (long) Math.sqrt(n))
                .noneMatch(i -> n % i == 0);
    }

    public LongStream numbers() {
        //注意这里filter方法的参数是一个Predict函数,这个函数是一个静态的方法
        //其方法签名和predict是一致的.因此可以使用类名::方法名 去调用.
        return iterate(2, i -> i + 1)
                .filter(Prime::isPrime);
    }

    public static void main(String[] args) {


        long l = System.currentTimeMillis();
        new Prime().numbers()
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        new Prime().numbers()
                .skip(90)
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
        long l1 = System.currentTimeMillis();
        System.out.println("used time" + (l1 - l));


    }
}