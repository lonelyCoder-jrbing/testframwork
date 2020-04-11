package com.testframwork.jdk8.jdk8Thingking.concurrent;

import com.testframwork.jdk8.jdk8Thingking.concurrent.utils.CompletableUtilities;

import java.util.concurrent.CompletableFuture;

public class CompletableOperations {

    static CompletableFuture<Integer> cfi(int i) {
        return CompletableFuture.completedFuture(Integer.valueOf(i));
    }

    public static void main(String[] args) {

        CompletableUtilities.showr(cfi(1));
        CompletableUtilities.voidr(cfi(2).runAsync(() ->
                System.out.println("runAsync")));
        CompletableUtilities.voidr(cfi(3).thenRunAsync(() ->
                System.out.println("thenRunAsync")));
        CompletableUtilities.voidr(CompletableFuture.runAsync(() ->
                System.out.println("runAsync is static")));

        CompletableUtilities.showr(CompletableFuture.supplyAsync(() -> 99));
        CompletableUtilities.voidr(cfi(4).thenAcceptAsync(i ->
                System.out.println("thenAcceptAsync: " + i)));

        CompletableUtilities.showr(cfi(5).thenApplyAsync(i -> i + 42));
        CompletableUtilities.showr(cfi(6).thenComposeAsync(i -> cfi(i + 99)));

        CompletableFuture<Integer> c = cfi(7);
        c.obtrudeValue(111);
        CompletableUtilities.showr(c);


    }
}
