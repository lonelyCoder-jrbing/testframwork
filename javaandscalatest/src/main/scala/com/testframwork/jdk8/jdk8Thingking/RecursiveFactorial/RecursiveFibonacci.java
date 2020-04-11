package com.testframwork.jdk8.jdk8Thingking.RecursiveFactorial;

public class RecursiveFibonacci {
    //使用实例变量进行调用
    IntCall fib;
    //构造器初始化的时候,实现算法


    public RecursiveFibonacci() {
        fib = n -> n == 0 ? 0 : n == 1 ? 1 : fib.call(n - 1) + fib.call(n - 2);
    }

    int fibonacci(int n) {
        return fib.call(n);
    }

    public static void main(String[] args) {
        RecursiveFibonacci rf = new RecursiveFibonacci();
        int fibonacci = rf.fibonacci(10);
        System.out.println("fibonacci:   " + fibonacci);

    }


}
