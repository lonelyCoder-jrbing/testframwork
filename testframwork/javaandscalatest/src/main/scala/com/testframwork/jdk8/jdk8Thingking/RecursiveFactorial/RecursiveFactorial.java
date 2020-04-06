package com.testframwork.jdk8.jdk8Thingking.RecursiveFactorial;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RecursiveFactorial {
    static IntCall fact;
    static Runnable run;

    public static void main(String[] args) throws InterruptedException {
        //相当于是阶乘的具体实现
        fact = n -> n == 0 ? 1 : n * fact.call(n - 1);
        run = () -> {
            int call = fact.call(10);
            System.out.println("result:   " + call);
        };

//        for (int i = 0; i <= 10; i++) {
//            //开始调用
////            fact.call(i);
//            System.out.println();
//
//        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for (int j = 0; j < 10; j++) {
            final int number = 3;
            Thread thread = new Thread(() -> {
                int call = fact.call(number);
                System.out.println("resut:  " + call);
            });
            String s = "shfaflajfljfljalfsalkfalkfsljejfl";
            Random r = new Random(1);
            int i = r.nextInt(s.length());
//            System.out.println("i:  "+i);
            int i1 = r.nextInt(s.length());
            int i2 = i1 == i ? i1 : r.nextInt(s.length());
//            System.out.println("i2:   "+i2);
            String threadName = null;
            if (i < i2) {
                threadName = s.substring(i, i2);
            } else {
                threadName = s.substring(i2, i);
            }
            thread.setName(threadName);
//            thread.start();
            executor.execute(thread);
            System.out.println("activeCount:   " + executor.getActiveCount());
//            executor.
            Thread.sleep(1000);

            System.out.println("threadName:   " + threadName);
            thread.join(10000);
//            number++;
        }
        executor.shutdown();
    }
}

