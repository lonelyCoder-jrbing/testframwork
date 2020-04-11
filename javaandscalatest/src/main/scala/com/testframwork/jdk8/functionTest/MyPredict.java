package com.testframwork.jdk8.functionTest;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyPredict {

    public static void main(String[] args) {
        testPredict();
        testpredict02();
        testpredict03();
    }

    //断言
    private static void testPredict() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        List<Integer> list = new ArrayList<>();
        for (int i : numbers) {
            list.add(i);
        }

        // 三个判断
        Predicate<Integer> p1 = i -> i > 5;
        Predicate<Integer> p2 = i -> i < 20;
        Predicate<Integer> p3 = i -> i % 2 == 0;
        List test = list.stream()
                .filter(p1
                        .and(p2)
//                        .and(Predicate.isEqual(8))
                        .and(p3))
                .collect(Collectors.toList());
        System.out.println(test.toString());
        //print:[6, 8, 10, 12, 14]
    }

    private static void testpredict02() {
        //使用predict的好处是将条件从业务逻辑中玻璃出来
        Predicate<String> predicate1 = s -> s.contains("a");
        Predicate<String> predicate2 = s -> s.contains("b");
        Predicate<String> predicate3 = predicate1.and(predicate2);
        System.out.println("predicate1" + predicate1);
        System.out.println("predicate2" + predicate2);
        System.out.println(predicate3.test("a"));
        System.out.println(predicate3.test("abc"));

    }

    private static void testpredict03() {
        Predicate<Integer> one = i -> i == 5;
        System.out.println(one.test(4));
        Predicate<String> str1 = str -> str.equals("byy");
        Predicate<String> str2 = s -> s.equals("jrbing");
        System.out.println((str1.or(str2)).test("jrbing"));
        int[] num = {1, 2, 3, 4, 5, 6};
//        String[] str = {"jrbing","man","marrued"};
        //此种方式只能将普通类型的数组转换为list，但是如果想将基本类型的数组转换，需要使用guava中的Ints.asList(num);
//        System.out.println(Arrays.asList(num).toString());
        List<Integer> integers = Ints.asList(num);
        List<Integer> collect = integers.stream().filter(one).collect(Collectors.toList());
        System.out.println(collect);
    }


}
