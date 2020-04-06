package com.testframwork.jdk8.streamTest.lambdaTest;

public class Demo01 {

    public static void main(String[] args) throws InterruptedException {
//能够接受lambda表达式的参数类型，是一个只包含一个方法的函数式接口。
        new Thread(() -> {
            System.out.println("hello thread");
        });
    }

    public void testhread() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("do service ....");
            }
        });
        System.out.println(thread.getName());
        thread.start();
        thread.join();
        System.out.println(thread.getName());
    }

}
