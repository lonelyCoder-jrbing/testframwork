package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//注意使用submit调用任务的执行的时候,是有返回值的,如果使用execute调用的时候,是没有返回值的.
public class LambdasAndMethodReferences {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> System.out.println("lambda 1"));//调用runnable
        //调用方法签名一致的notrunnable中的run()
        executorService.submit(new NotRunnable()::go);
        //调用notcallable中的get()方法
        Future<Integer> lambda2 = executorService.submit(() -> {
            System.out.println("lambda2");
            return -1;
        });
        Integer lambda2Value = lambda2.get();
        System.out.println("lambda2Value:    " + lambda2Value);
        //有返回值的,将返回值存储在future方法中.
        Future<Integer> value3 = executorService.submit(new NotCallable()::get);
        System.out.println("value3:   " + value3.get());
        executorService.shutdown();
    }
}
