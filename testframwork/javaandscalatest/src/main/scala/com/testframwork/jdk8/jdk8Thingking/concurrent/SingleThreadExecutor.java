package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class SingleThreadExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //此处mapToObj需要的参数是IntFunction<? extends U> mapper
        IntFunction<NapTask> function = x -> new NapTask(x);
        //说明Intfunction是需要掺入一个int类型的值,然后返回一个任意类型对象
        IntStream.range(0, 10).mapToObj(NapTask::new).forEach(executorService::execute);
        //这里forEach的参数需要一个consumer,而consumer的accept方法的方法签名和excute一致
        // ,因此可以java可以通过方法签名一致进行调用
        executorService.shutdown();


        while (!executorService.isTerminated()) {
            System.out.println(Thread.currentThread().getName() + "awaiting terminate");
        }
        new Nap(0.1);
    }


}
