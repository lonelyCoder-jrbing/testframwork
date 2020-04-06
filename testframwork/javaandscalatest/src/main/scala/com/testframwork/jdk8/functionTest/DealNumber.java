package com.testframwork.jdk8.functionTest;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DealNumber implements Consumer<Integer> {

    private MyConsumerTest test;

    public DealNumber(MyConsumerTest test) {
        this.test = test;
        test.ints = new ArrayList<Integer>();
    }

    @Override
    public void accept(Integer integer) {
        if (integer > 3) {
            test.ints.add(integer);
        }
    }

}
