package com.testframwork.threadtest;

import java.util.concurrent.*;

/**
 *1. 创建线程池将创建的线程放入线程池中，并且在线程的run方法中调用latch.countDown()
 *2, 将所有的线程在线程池中执行
 *3. 执行latch.await()方法
 * 1. 创建线程池将创建的线程放入线程池中，并且在线程的run方法中调用latch.countDown()
 * 2, 将所有的线程在线程池中执行
 * 3. 执行latch.await()方法
 * 目的：达到所有线程并行执行的目的
 */
public class CountDownLatchTest {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException{
        System.out.println("主线程开始......");
        long startAt = System.currentTimeMillis();
        System.out.println("startAt: "+System.currentTimeMillis());
        final int[] atest = {0};
        Thread thread1 = new Thread(()->{
            try {
                int anInt = getInt();
                atest[0] = anInt;
                Thread.sleep(1000);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 3000,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(4));
        threadPoolExecutor.execute(thread1);
        threadPoolExecutor.execute(thread2);
        threadPoolExecutor.shutdown();
        latch.await();
        long endTime = System.currentTimeMillis();
        System.out.printf("used time "+(endTime-startAt));
        System.out.println("atest[0]:  "+atest[0]);
        System.out.printf("used time " + (endTime - startAt));
        System.out.printf("主线程等待。。。");
        System.out.println(latch.toString());
        System.out.println("主线程继续.......");
    }

    public static int getInt() {
        int a = 1;
        return a;
    }


    public static class Worker implements Runnable {

        @Override
        public void run() {
            System.out.println("子线程任务正在执行");
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){

            }finally {
                latch.countDown();
            }
        }
    }
}
