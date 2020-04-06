package com.testframwork.jdk8.streamTest.lambdaTest.groupTest;


import com.testframwork.jdk8.streamTest.Teacher;
import com.testframwork.jdk8.streamTest.lambdaTest.Utils.CollectionProviders;
import com.testframwork.jdk8.streamTest.lambdaTest.reduceTest.Demo04;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.testframwork.jdk8.streamTest.lambdaTest.reduceTest.Demo04.teacherList;

public class Demo02 {

    public static void main(String[] args) {
        List<String> items = Arrays.asList("apple", "apple", "banana", "banana", "orange", "papaya");
        Map<String, Long> map = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
        CollectionProviders.ListProvider provider = CollectionProviders.getProvider();
        List<Teacher> teacherList =provider.getTeacherList();
        Map<Optional<String>, Long> collect = Demo04.teacherList.stream().collect(Collectors.groupingBy(Teacher::getName, Collectors.counting()));
        System.out.println(collect);


        System.out.println("teacherList: " + Demo04.teacherList);
        List<Optional<Double>> collect1 = Demo04.teacherList.stream().map(Teacher::getIncome).filter(el -> Objects.nonNull(el)).collect(Collectors.toList());
        System.out.println("collect3:    " + collect1);
        Map<String, Long> finalMap = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                ).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        System.out.println("finalMap:  " + finalMap);
        Map<String, Integer> collect2 = Demo04.teacherList.stream().filter(e -> Objects.nonNull(e.getIncome())).collect(Collectors.groupingBy(t1 -> t1.getName().get(), Collectors.summingInt(t -> t.getAge().get())));
        System.out.println("collect2:    " + collect2);


        System.out.println(Demo04.teacherList);
        Map<String, List<Optional<Double>>> collect3 = Demo04.teacherList.stream().filter(e -> Objects.nonNull(e.getIncome())).
                collect(Collectors.groupingBy(t -> t.getName().get(), Collectors.mapping(t -> t.getIncome(), Collectors.toList())));

        System.out.println("collect3:   " + collect3);

        System.out.println("========================joining==============================");
        String collect4 = Demo04.teacherList.stream().map(t -> t.getName().get()).collect(Collectors.joining());
        System.out.println(collect4);
        //===============================partition==================================
        Map<Boolean, List<Teacher>> collect5 = Demo04.teacherList.stream().collect(Collectors.partitioningBy(t -> t.getAge().get() > 26));

        System.out.println("collect5:   " + collect5);


    }


}
