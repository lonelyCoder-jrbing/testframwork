package com.testframwork.jdk8.functionTest.ApplyTest;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

public class Test02 {

    @Test
    public void test01() {
        int ret = getRet(20, x -> x + 20);
        System.out.println(ret);
        int ret2 = getRet2(2, 2.0, x -> x + 1);
        System.out.println("ret2:    " + ret2);
    }

    public int getRet(int num, Function<Integer, Integer> function) {
        Function<Integer, Integer> squre = x -> new BigDecimal(x).multiply(new BigDecimal(x)).intValue();
        //先进行apply中的操作，然后进行andThen中的操作。
        return function.andThen(squre).apply(num);
    }

    public int getRet2(int num, Double num2, Function<Integer, Integer> function) {
        Function<Integer, Integer> divide = x -> new BigDecimal(x).add(new BigDecimal(num)).divide(new BigDecimal(num2)).intValue();
        //compose 首先进行compose中的操作，然后进行apply中的操作。
        return function.compose(divide).apply(num);
    }


}
