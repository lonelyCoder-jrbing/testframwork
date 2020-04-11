package com.testframwork.jdk8.streamTest.lambdaTest.reduceTest;

import com.testframwork.jdk8.streamTest.lambdaTest.reduceTest.entities.User;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo01 {


    private static List<User> users = Arrays.asList(
            new User(1, "张三", 12, User.Sex.MALE),

            new User(2, "李四", 21, User.Sex.FEMALE),

            new User(3, "王五", 32, User.Sex.MALE),

            new User(4, "赵六", 32, User.Sex.FEMALE));

    public static void main(String[] args) {
        System.out.println("===testAverage=============");
        testAverage();
        System.out.println("====getRangeage============");
        getRangeage();
        //====getUserNumByGender=====
        getUserNumByGender();

        getUserNameByGender();
        getSumAgeGroupingByGender();
        getAverageNumByGender();
        getReduceAge();
    }

    public static void testAverage() {

        User.Averager averageCollect = users.parallelStream()

                .filter(p -> p.getGender() == User.Sex.MALE)

                .map(User::getAge)

                .collect(User.Averager::new, User.Averager::accept, User.Averager::combine);


        System.out.println("Average age of male members: "

                + averageCollect.average());

    }

    public static void getRangeage() {
        List<User> reangeAge = users.stream().filter(e -> e.getAge() > 12).collect(Collectors.toList());
        reangeAge.forEach(e -> System.out.println(e.getName()));
    }

    //按照性别统计用户数量
    public static void getUserNumByGender() {
        Map<User.Sex, Integer> genderNumMap = users.stream().collect(Collectors.groupingBy(User::getGender, Collectors.summingInt(p -> 1)));
        System.out.println(genderNumMap);
    }

    //按照性别获取用户名称
    public static void getUserNameByGender() {
        Map<User.Sex, List<String>> getUserNameByGenderMap = users.parallelStream().collect(Collectors.groupingBy(User::getGender, Collectors.mapping(User::getName, Collectors.toList())));
        Iterator<Map.Entry<User.Sex, List<String>>> iterator = getUserNameByGenderMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<User.Sex, List<String>> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());
        }
    }

    //按照性别统计年龄总和
    public static void getSumAgeGroupingByGender() {
        Map<User.Sex, Integer> getSumAgeGroupingByGenderMap = users.parallelStream().
                collect(Collectors.groupingBy(User::getGender, Collectors.reducing(0, User::getAge, Integer::sum)));

        Iterator<Map.Entry<User.Sex, Integer>> iterator = getSumAgeGroupingByGenderMap.entrySet().iterator();
        while (iterator.hasNext()) {

            Map.Entry<User.Sex, Integer> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());

        }

    }

    //按照性别求年龄平均值
    public static void getAverageNumByGender() {
        Map<User.Sex, Double> getAverageNumByGenderMap = users.parallelStream().collect(Collectors.groupingBy(User::getGender, Collectors.averagingInt(User::getAge)));
        Iterator<Map.Entry<User.Sex, Double>> iterator = getAverageNumByGenderMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<User.Sex, Double> next = iterator.next();
            System.out.println(next.getKey());
            System.out.println(next.getValue());

        }
    }

    //求所有的用户的年龄总和
    public static void getReduceAge() {
//        int sum = users.parallelStream().mapToInt(User::getAge).sum();
        int sum = users.parallelStream().mapToInt(User::getAge).reduce(0, (x, y) -> x + y);
        System.out.println("求所有的用户的年龄总和" + sum);
    }

}
