package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SingleThreadExcutorTest02 {
    public static void main(String[] args) {


        //是线程安全的
        //因为i而每次运行一个任务,所以这些任务之间不会相互干扰,因此加强了线程的安全型,这种现象叫做
        //线程限制,因为单线程上运行任务限制了他们之间的影响.线程限制限制了加速,但是省了很长时间去调试和重写.
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.range(0, 10).mapToObj(InterferingTask::new).forEach(executorService::execute);
        executorService.shutdown();

    }
}
