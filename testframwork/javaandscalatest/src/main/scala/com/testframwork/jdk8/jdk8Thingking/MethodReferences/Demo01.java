package com.testframwork.jdk8.jdk8Thingking.MethodReferences;

import java.util.function.Function;
import java.util.function.IntToLongFunction;

public class Demo01 {

    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        demo01.test(1, 2, test01);
        int i = demo01.test01(10, function);
        System.out.println("function test:   " + i);
        long l = f8.applyAsLong(10);
        System.out.println("long:   " + l);
        demo01.test02(10, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                double random = Math.random();
                return (int) (integer + random);
            }
        });

    }

    static IntToLongFunction f8 = i -> i;

    static ReduceInterface test01 = (x, y) -> (x + y);
    static Function<Integer, Integer> function = x -> x.hashCode();

    public void test(int i, int j, ReduceInterface test01) {
        int reduce = test01.reduce(i, j);
        System.out.println(reduce);
    }


    public int test01(int x, Function<Integer, Integer> function) {
        return function.apply(x);
    }

    public int test02(int x, Function<Integer, Integer> function) {
        return function.apply(x);
    }


}
