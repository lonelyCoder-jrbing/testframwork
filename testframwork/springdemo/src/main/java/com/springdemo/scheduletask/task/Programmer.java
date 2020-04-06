package com.springdemo.scheduletask.task;

public class Programmer extends People {

    @Override
    public String name() {
        return "programmer B";
    }

    @Override
    public void work() {
        System.out.println("coding all day...");
    }
}
