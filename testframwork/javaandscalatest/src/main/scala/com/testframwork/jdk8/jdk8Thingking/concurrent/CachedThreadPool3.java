package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CachedThreadPool3 {
    public static Integer extractResult(Future<Integer> f) {
        try {
            return f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        //callable通过返回值将每一个线程的变量值进行返回,这样在一定程度上保证了共享变量的安全.
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CountingTask> tasks = IntStream.range(0, 10).mapToObj(CountingTask::new).collect(Collectors.toList());
        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        int sum = futures.stream().mapToInt(CachedThreadPool3::extractResult).reduce(0, Integer::sum);
        System.out.println("sum:   " + sum);
        executorService.shutdown();
    }


}
