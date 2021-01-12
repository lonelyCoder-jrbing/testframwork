package com.testframwork.threadtest.ForkJoinPool;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * create by sumerian on 2020/11/8
 * <p>
 * desc:ForkJoinPool 主要用于实现“分而治之”的算法，特别是分治之后递归调用的函数，例如 quick sort 等。
 **/
public class App {
    public static void main(String[] args) {

        Calculator calculator = new ForkJoinCalculator();
        long[] numbers = new long[1000000000];
        for (int i = 0; i < 1000000000; i++) {
            numbers[i] = i;
        }

        calculator.sumUp(numbers);
    }
//

    /****
     * 使用并行流的方式进行计算
     * 并行流底层还是Fork/Join框架，只是任务拆分优化得很好。
     * 耗时效率方面解释：Fork/Join 并行流等当计算的数字非常大的时候，
     * 优势才能体现出来。也就是说，如果你的计算比较小，或者不是CPU密集型的任务，不太建议使用并行处理
     * @return
     */
    private long caculate() {
        Instant start = Instant.now();
        long result = LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
        System.out.println("结果为：" + result); // 打印结果500500
        return result;

    }


}
