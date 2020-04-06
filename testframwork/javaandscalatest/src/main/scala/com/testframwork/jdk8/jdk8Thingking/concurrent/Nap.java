package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.concurrent.TimeUnit;

public class Nap {
    public Nap(Double t) {
        try {
            TimeUnit.MILLISECONDS.sleep((int) (1000 * t));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Nap(Double t, String msg) {
        this(t);
        System.out.println(msg);
    }
}
