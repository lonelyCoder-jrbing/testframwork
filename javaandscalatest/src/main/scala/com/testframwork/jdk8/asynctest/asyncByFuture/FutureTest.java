package com.testframwork.jdk8.asynctest.asyncByFuture;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Slf4j
public class FutureTest {
    //使用future的这种方式将会阻塞主线程，从而无法实现异步调用
    //如果不需要返回值的话，是可以接受的

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
        System.out.println("main函数开始执行");
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
                return 3;
            }
        }, executor);
        future.thenAccept(e -> System.out.println(e));
        System.out.println("main函数执行结束");


    }

}
