package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuittingCompletable {
    public static void main(String[] args) {
        List<QuittableTask> tasks = IntStream.range(0, QuittingTasks.count).mapToObj(QuittableTask::new).collect(Collectors.toList());
        List<CompletableFuture<Void>> cfutures = tasks.stream().map(CompletableFuture::runAsync).collect(Collectors.toList());
        new Nap((double) 1);
        tasks.forEach(QuittableTask::quit);
        cfutures.forEach(CompletableFuture::join);
    }
}
