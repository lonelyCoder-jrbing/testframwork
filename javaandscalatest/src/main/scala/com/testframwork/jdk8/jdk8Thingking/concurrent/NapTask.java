package com.testframwork.jdk8.jdk8Thingking.concurrent;

public class NapTask implements Runnable {
    final int id;

    public NapTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        new Nap(0.1);
        String name = Thread.currentThread().getName();
        System.out.println("thread name : " + name);
    }


    @Override
    public String toString() {
        return "NapTask{" +
                "id=" + id +
                '}';
    }
}
