package com.testframwork.jdk8.functionTest;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class MyConsumerTest {

    List<Integer> ints;

    public static void main(String[] args) {
        TaskOrderDO t = new TaskOrderDO();
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        MyConsumerTest myConsumerTest = new MyConsumerTest();
        integers.forEach(new DealNumber(myConsumerTest));
        System.out.println("size:   " + myConsumerTest.ints.size());
        myConsumerTest.ints.forEach(e -> {
            System.out.println(e);
        });
    }
}
