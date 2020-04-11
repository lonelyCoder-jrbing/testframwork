package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.stream.IntStream;

public class InterferingTask implements Runnable {
    final int id;

    private static Integer val = 0;

    public InterferingTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        IntStream.range(0, 100).map(e -> {
            val++;
            return e;
        }).forEach(e -> {

            System.out.println(id + "     " + Thread.currentThread().getName() + " " + val);

        });
    }
}
