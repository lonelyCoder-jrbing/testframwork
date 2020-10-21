package com.testframwork.jdk8.functionTest.ApplyTest;

import com.github.benmanes.caffeine.cache.CacheLoader;

import java.util.Comparator;
import java.util.function.Function;

public class Test01 {
    //使用Function进行穿参的两种实现方式。
    public static void main(String[] args) {
        int myNumber = 10;

        // 使用lambda表达式实现函数式接口
        // (x)->(x)+20 输入一个参数x，进行加法运算，返回一个结果
        // 所以该lambda表达式可以实现Function接口

        Function<Integer, Integer> function = e -> e + 1;
//    int res1 = modifyTheValue(myNumber, (x) -> x + 20);
        int res1 = modifyTheValue(myNumber,  e -> e + 1);
        System.out.println(res1); // 30
        //  使用匿名内部类实现
        int res2 = modifyTheValue(myNumber, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t + 20;
            }
        });
        System.out.println(res2); // 30

    }

    static int modifyTheValue(int valueToBeOperated, Function<Integer, Integer> function) {
        return function.apply(valueToBeOperated);
    }
}
