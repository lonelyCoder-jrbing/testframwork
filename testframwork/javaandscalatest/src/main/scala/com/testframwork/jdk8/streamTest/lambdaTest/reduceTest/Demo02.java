package com.testframwork.jdk8.streamTest.lambdaTest.reduceTest;


import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

//reduce原理探索
public class Demo02 {

    @Test
    public void test01() {
        //取出out

        PrintStream out = System.out;
//实现Predicate接口并且指定行为：传入的参数中是否包含字符串"a"
        //后续可以调用该接口的test方法做筛选判断

        Predicate<String> predicate = x -> x.contains("a");
        out.println("单个参数的reduce方法->接收BinaryOperator函数返回一个Optional<T>类型\n" +
                "实际上，该方法此时的表现为将该序列(Stream流内的类型)\n" +
                "的第一个元素与该流后续所有元素做2合计算'比如：(a[0]+a[1])+a[3]'\n" +
                "执行完函数后获得一个Optional<T>类型（可选的，任意的）后调用get()方法进行取值");

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        Integer integer = integerStream.reduce((x, y) -> x * y).get();
        System.out.println("乘积和：" + integer);
        out.println("两个参数1reduce方法实际上只是多了一个初始化的值‘T’\n" +
                "，第二个参数与单参方法一致,该重载方法返回类型为‘T’，指通过BinaryOperator进行计算后\n" +
                "返回一个类型与初始化参数的类型一致的值:::重点->双参函数与单参的计算流程不同：\n" +
                "该函数是将初始化参数与流内所有的元素逐个进行二和运算，\n" +
                "下面实例为将所有包含'a'的元素拼接在初始化参数的后面");

        Stream<String> stream3 = Stream.of("as1", "a12", "nmm1", "cc2", "ac3", "ab4");
        String reduce = stream3.reduce("", (x, y) -> {
            if (predicate.test(y)){
                return x.concat(y);
            }

            else {
                return x;
            }
        });
        System.out.println("reduce result :" + reduce);
        Stream<String> stream4 = Stream.of("as1", "a12", "nmm1", "cc2", "ac3", "ab4");
        //这种用法的作用也可以使用max代替
        String reduce1 = stream4.reduce("", (x, y) -> x.length() - y.length() > 0 ? x : y);
        System.out.println("reduceTest:   " + reduce1);


        out.println("初始化参数为一个集合，将流内所有符合条件的元素筛选出来加入到该初始参数中");
        Stream<String> stream = Stream.of("as", "ai", "nmm", "cc", "ac", "ab");
        //输出结果为：as ai ac ab
        stream.reduce(new ArrayList<String>(), (x, y) -> {
            if (predicate.test(y)) {
                x.add(y);
            }
            return x;
        }, (x, y) -> x).forEach(System.out::println);

        out.println("并行(parallel)的影响下，第三个参数才会生效\n" +
                "该状态下理解为第二个函数根据流内数据的个数分为多线程去处理每个值与首参U的2合计算\n" +
                "首参U分别与流内每个值计算完毕后，由第三个参数对这些值做出整合(该参数要求实现\n" +
                "BinaryOperator接口并给出一个单值计算的行为),即，\n" +
                "该接口内三个参数为同一类型，并作出操作(T x,T y)->{return ?(T)}");
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        //进行两次聚合
//        Integer reduce1 = stream1.reduce(10, (x, y) -> x + y, (x, y) -> x * y);
        //11*13*16
        Integer reduce2 = stream1.parallel().reduce(10, (x, y) -> x + y, (x, y) -> x * y);

//        System.out.println("reduce1"+reduce1);
        System.out.println("reduce2:" + reduce2);
        out.println("非并行情况下的第三个参数BinaryOperator " +
                "combiner(合成器)\n会对第二个参数BiFunction accumulator(累加器) 产生什么影响？\n" +
                "会对该函数运行结果产生什么影响？\n" +
                "答案是：第三个参数无效。\n" +
                "符合预期的说法：非并行情况下第三个参数根本就不需要，不会对该函数产生任何影响\n" +
                "Debug:该函数执行完第二个参数后直接停止运行，根本没有访问到第三个参数去执行");


    }

}
