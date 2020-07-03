package com.testframwork.vm.lock.synchronizetest;


import java.util.Vector;

/**
 * create by sumerian on 2020/7/2
 * <p>
 * desc: 偏向锁，轻量级锁，重量级锁测试
 * 特别注意启动时要加上参数：‐XX:BiasedLockingStartupDelay=20000 ‐XX:BiasedLockingStartupDelay=0,
 * 否则启动的将是轻量级锁
 **/
public class BiasLockTest {


    public static void main(String[] args) throws Exception {
        A a = new A();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000L; i++) {
            a.parse();
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));
    }

}
