package com.testframwork.jdk8.asynctest.asyncForSpring;

import org.junit.Test;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public class MyService {

    @Test
    public void test3() throws Exception {
        System.out.println("main函数开始执行");
        MyService myService = new MyService();
        myService.longtime();
        System.out.println("main函数执行结束");
    }

    @Async
    public void longtime() {
        System.out.println("我在执行一项耗时任务");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成");

    }

}
