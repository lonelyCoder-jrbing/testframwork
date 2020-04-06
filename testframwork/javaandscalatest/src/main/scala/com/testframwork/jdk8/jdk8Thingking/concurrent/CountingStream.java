package com.testframwork.jdk8.jdk8Thingking.concurrent;

import java.util.stream.IntStream;

public class CountingStream {
    public static void main(String[] args) {
        //我们需要做的就是将paralell插入到其他的顺序操作中,
        //然后一切都顺序进行
        System.out.println(IntStream.range(0, 10).parallel().mapToObj(CountingTask::new).map(ctx -> {
            try {
                System.out.println("call方法返回值:   " + " " + ctx.call());
                return ctx.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).reduce(0, Integer::sum));

    }


}
