package com.testframwork.vm.lock.synchronizetest;

import com.testframwork.vm.lock.synchronizetest.A;

import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/7/2
 * <p>
 * desc:偏向锁，轻量级锁，重量级锁测试
 **/
public class LightlyLocKTest {
    public static void main(String[] args) throws Exception {
        A a = new A();
        TimeUnit.SECONDS.sleep(5);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            a.parse();
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));
    }



}
