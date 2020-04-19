package com.testframwork.jdk8.asynctest.asyncforjava;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/***
 *
 *使用CompletableFuture的方式并行处理耗时任务
 *
 */
public class Shop {

    public Future<Double> getPriceAsync(String product) {
        // 创建CompletableFuture对象，他会包含计算结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        // 在另一个线程中异步执行
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                // 需长时间计算的任务结果并得出结果时，设置Future的返回值
                futurePrice.complete(price);
            } catch (Exception ex) {
                //为了让客户端能了解商店无法提供请求商品价格的原因，
                // 你需要使用CompletableFuture的completeExcep-tionally方法将导致CompletableFuture内发生问题的异常抛出。
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        // 无需等待还没结束的计算，直接返回结果
        return futurePrice;
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {

        }
    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        long startTime = System.currentTimeMillis();
        Future<Double> priceAsync = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.currentTimeMillis() - startTime) / 1_000_000);
        System.out.println("invocation return after " + invocationTime + "msecs");
        delay();
        long timeUsed  = System.currentTimeMillis()-startTime;
        System.out.println("total time used:  "+timeUsed);
        try {
            double price = priceAsync.get();
            System.out.println("price is %.2f&n" + price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}