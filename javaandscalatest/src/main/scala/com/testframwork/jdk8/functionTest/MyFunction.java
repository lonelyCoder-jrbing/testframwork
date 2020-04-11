package com.testframwork.jdk8.functionTest;

import java.util.function.Function;

public class MyFunction implements Function<Integer, String> {


    @Override
    public String apply(Integer integer) {
        String s = String.valueOf(integer);
        return s;
    }
}
