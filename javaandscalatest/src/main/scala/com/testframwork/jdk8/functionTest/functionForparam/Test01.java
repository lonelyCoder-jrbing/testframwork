package com.testframwork.jdk8.functionTest.functionForparam;


import com.testframwork.jdk8.streamTest.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Test01 {

    public static void main(String[] args) {
        //
//    Function function = new MyFunction();
        Function<Integer, String> str = e -> String.valueOf(e);
        Function<Teacher, Integer> toInteger = e -> e.getAge().get();
        Function<Integer, Teacher> toTeacher = e -> {
            Teacher t = new Teacher();
            t.setAge(Optional.of(e));
            return t;
        };


        Iterable list = getList(str);
        if (list instanceof ArrayList) {
            System.out.println("是一个ArrayList。。。");
            list.forEach(Test01::printnum);
        }

    }

    private static void printnum(Object o) {
        System.out.println(o);
    }

    public static <T> Iterable<String> getList(Function<Integer, String> str) {
        List<Integer> list = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(e -> {
            String stringValue = str.apply(e);
            newList.add(stringValue);
        });
        return newList;
    }

}
