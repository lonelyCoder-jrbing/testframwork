package com.springdemo.scheduletask.task;

public class Teacher extends People {

    @Override
    public String name() {
        return "teacher a";
    }

    @Override
    public void work() {
        System.out.println("teach service important..");
    }
}
