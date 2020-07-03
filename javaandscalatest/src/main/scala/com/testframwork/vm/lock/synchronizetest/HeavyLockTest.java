package com.testframwork.vm.lock.synchronizetest;

import com.testframwork.vm.lock.synchronizetest.A;

import java.util.concurrent.CountDownLatch;
/**
 * create by sumerian on 2020/7/2
 * <p>
 * desc: 偏向锁，轻量级锁，重量级锁测试
 **/
public class HeavyLockTest {
    static CountDownLatch countDownLatch = new CountDownLatch(1000000000);

    public static void main(String[] args) throws InterruptedException {
        final A a = new A();

        long start = System.currentTimeMillis();

        for(int i=0;i<2;i++){
            new Thread(()->{
                while(countDownLatch.getCount()>0){
                    a.parse();
                }
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));
    }
}
