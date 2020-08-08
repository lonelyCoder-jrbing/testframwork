package com.testframwork.threadtest.threadpool;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * https://www.cnblogs.com/jay-huaxiao/p/11454416.html
 * create by sumerian on 2020/8/7
 * <p>
 * desc:  各种线程池的功能比较
 **/
public class ThreadpoolDemo {


    public static void main(String[] args) {
//        demo01();
//        demmo02();
//        demo03();
        demo04();

    }

    /***
     * FixedThreadPool 适用于处理CPU密集型的任务，
     * 确保CPU在长期被工作线程使用的情况下，尽可能的少的分配线程，即适用执行长期的任务
     * 指定容量的线程池， 并且指定了任务的拒绝策略
     * @return
     */
    public static String demo01() {
//                         Executors.newFixedThreadPool(10);
        ExecutorService executor = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(Integer.MAX_VALUE / 100),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
                    }
                });


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return "";

    }

    /***
     *  CachedThreadPool用于并发执行大量短期的小任务
     * @return
     */
    public static String demmo02() {
        ExecutorService service =
//                Executors.newCachedThreadPool();
                new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                        1L, TimeUnit.SECONDS,
                        new SynchronousQueue<Runnable>());
        IntStream.range(0, Integer.MAX_VALUE).forEach(el -> {
            service.submit(() -> {
                System.out.println("el---->" + el);
                System.out.println("thread name---->" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        });
        return "";
    }


    /****
     * SingleThreadExecutor:
     * 适用于串行执行任务的场景，一个任务一个任务地执行。
     * @return
     */
    public static String demo03() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "正在执行");
            });
        }
        return "";
    }

    /****
     * ScheduledThreadPool 周期性执行任务的场景，需要限制线程数量的场景
     * @return
     */
    public static String demo04() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        for (int i = 0; i < 5; i++) {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread().getName() + "正在执行" + "currentTime:{}" + System.currentTimeMillis());
        }, 1, 3, TimeUnit.SECONDS);
//        }
        return "";
    }


}
