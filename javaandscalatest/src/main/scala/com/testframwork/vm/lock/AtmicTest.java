package com.testframwork.vm.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by sumerian on 2020/7/1
 * <p>
 * desc:cas 自旋锁的测试
 **/
public class AtmicTest {
    public static AtomicInteger race = new AtomicInteger(0);

    public static CountDownLatch latch = new CountDownLatch(20);


    public static void increase() {
        System.out.println("increase......." + race.get());
        race.incrementAndGet();
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < THREAD_COUNT; i++) {
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i = 0; i < 100; i++) {
//                        System.out.println("==========="+i);
//                        increase();
//                    }
//                }
//            });
//            threads[i].start();
            service.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    System.out.println("===========" + j);
                    increase();
                }
//               increase();
                latch.countDown();
            });


        }
        //等待所有累加线程都结束
//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }
        try {
            latch.await();
        } catch (InterruptedException e) {

        } finally {
            service.shutdown();

        }
        System.out.println("race" + race);
    }
}
