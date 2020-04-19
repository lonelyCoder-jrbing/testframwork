package com.testframwork.jdk8.asynctest.asyncforjava;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Slf4j
public class FutureTest {
    //使用future的这种方式将会阻塞主线程，从而无法实现异步调用
    //如果不需要返回值的话，是可以接受的
    //异步调用：  无需等待被调用函数的返回值，就让操作系统继续进行的调用方法
    @Test
    public void test01() throws IOException, ExecutionException, InterruptedException {
//    Logger("============maink begin ===================");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Object> future = executorService.submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {
//        log.info("task   begin...");
                Thread.sleep(2000);
//        log.info("task     end......");
                return 3;
            }
        });
//    Integer integer = (Integer) future.get();
//    log.info("===========result=============={}");
//    log.info("===============main   end=========================");
//    System.in.read();
    }

    @Test
    public void test2() throws Exception {
        long  start = System.currentTimeMillis();

        System.out.println("main函数开始执行");
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("===task start===");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===task finish===");
                latch.countDown();
                return 3;
            }
        }, executor);
        CompletableFuture.runAsync(()->{
            try {
                delay(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },executor);
        CompletableFuture<Integer> integerCompletableFuture = future.thenApply((Integer i) -> {
            return i + 1;
        });


        integerCompletableFuture.whenComplete((result, exception) -> {
            if (null == exception) {
                System.out.println("result from future task " + result);
            } else {
                System.out.println("throw new exception from future task: " + exception);
            }
        });

        latch.await();
        long timeUsed = System.currentTimeMillis()-start;
        System.out.println("timeUsed="+timeUsed);
        System.out.println("main函数执行结束");


    }

    public static void delay(long time) throws InterruptedException {
        TimeUnit.SECONDS.sleep(time);
    }


}
