package com.testframwork.jdk8.jdk8Thingking.strategy;

public class Soft implements Strategy {
    //为默认的策略
    @Override
    public String approach(String msg) {
        return msg.toLowerCase() + "    soft  imp?";
    }
}
