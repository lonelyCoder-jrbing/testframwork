package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuittingTasks {
    public static final int count = 150;


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<QuittableTask> tasks = IntStream.range(0, count).mapToObj(QuittableTask::new)
                .peek(qt -> executorService.execute(qt)).collect(Collectors.toList());
        new Nap((double) 1);
        tasks.forEach(QuittableTask::quit);
        executorService.shutdown();


    }


}
