package com.testframwork.jdk8.jdk8Thingking.flapMapTest;

import java.io.IOException;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCount02 {
    public static void main(String[] args) throws IOException {
        FileToWords.stream("D:\\code\\springCloud\\words")
                .collect(Collectors.groupingBy(e -> e.toLowerCase(),
                        Collectors.summingInt(p -> 1))).forEach((k, v) -> {
            System.out.println(k + "===" + v);

        });

//
        System.out.println("  ========version2==collectors中的reducing函数========");
        //使用自定义的binaryoperator
        FileToWords.stream("D:\\code\\springCloud\\words").collect(
                Collectors.groupingBy(e -> e.toLowerCase(), Collectors.reducing(0, p -> 1, (x, y) -> accumulator01(x, y, (i, j) -> x + y))));
        //jdk8方法引用
        FileToWords.stream("D:\\code\\springCloud\\words").collect(
                //思考为何不能使用jdk8方法引用方式进行调用
                Collectors.groupingBy(e -> e.toLowerCase(), Collectors.reducing(0, p -> 1, WordCount02.accumulator())));
        //使用函数式接口
        FileToWords.stream("D:\\code\\springCloud\\words").collect(
                //思考为何不能使用jdk8方法引用方式进行调用
                Collectors.groupingBy(e -> e.toLowerCase(), Collectors.reducing(0, p -> 1, operator)));

    }

    public static BinaryOperator<Integer> accumulator() {
        return (i, j) -> i + j;
    }

    private static Integer accumulator01(Integer x, Integer y, BinaryOperator<Integer> operator) {
        return operator.apply(x, y);
    }

    public static BinaryOperator<Integer> operator = (x, y) -> x + y;
    //    static Integer x = 1;
    private static Function<Integer, Integer> function = (x) -> x + 1;
}
