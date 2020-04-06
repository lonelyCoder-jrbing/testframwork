package com.testframwork.jdk8.streamTest.lambdaTest.buildPojo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo02 {
    public static void main(String[] args) {

//unLimitStreamTest();
//        unlimitStream2();
        test01();
    }

    public static void unLimitStreamTest() {

        Stream.generate((() ->
                "number" + new Random().nextInt())).limit(20).forEachOrdered(System.out::println);
    }

    public static void unlimitStream2() {
        ArrayList<Integer> arrayList = new ArrayList();
        Map<Integer, String> map = new HashMap();
        Stream.iterate(0, x -> x + 1).limit(10).forEachOrdered(i -> {
            map.put(i, "name" + i);
        });

        List<String> nameList = new LinkedList<String>();
        map.forEach((k, v) -> {
            if (k > 3) {
                nameList.add(v);
            }
        });
        nameList.forEach(System.out::println);
    }

    public static void testMap() {
        List<Integer> list = new ArrayList<>();
        Stream.iterate(0, i -> i + 1).limit(10).forEachOrdered(e -> list.add(e));
        List<String> collect = list.stream().filter(e -> e > 3).limit(3).map(el -> String.valueOf(el)).collect(Collectors.toList());
    }

    public static void test01() {
        List<Student> studentsList = new ArrayList<Student>() {
            {
                for (int i = 0; i < 10; i++) {
                    add(Student.builder().age(i).name("name" + i));
                }
            }
        };
        studentsList.forEach(System.out::println);
    }

}
