package com.testframwork.vm.lock.synchronizetest;

/**
 * create by sumerian on 2020/7/2
 * <p>
 * desc: 偏向锁，轻量级锁，重量级锁测试
 **/
public class A {

    int i;
    public synchronized void parse(){
        i++;
    }
}