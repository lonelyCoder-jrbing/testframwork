package com.testframwork.jdk8.streamTest.lambdaTest.reduceTest;


import com.testframwork.jdk8.streamTest.Teacher;
import com.testframwork.jdk8.streamTest.lambdaTest.Utils.CollectionProviders;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

//reduce 的原理
public class Demo04 {
    public static void main(String[] args) {
        ArrayList<Integer> list = Stream.of(1, 2, 3, 4).reduce(new ArrayList<Integer>(), new BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> apply(ArrayList<Integer> list, Integer item) {
//                item += item;
                list.add(item);
                return list;
            }
        }, new BinaryOperator<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> apply(ArrayList<Integer> acc, ArrayList<Integer> list) {
                System.out.println("acc:      " + acc);
                System.out.println("listL:    " + list);
                acc.addAll(list);
                return acc;
            }
        });
        list.forEach(System.out::println);
    }

    public static List<Teacher> teacherList = null;

    @Before
    public void before() {
        teacherList = CollectionProviders.getProvider().getTeacherList();
        System.out.println(teacherList);
    }

    @Test
    public void test01() {
        List<Teacher> teacherList = CollectionProviders.getProvider().getTeacherList();
        Teacher reduce = teacherList.stream().reduce(new Teacher(Optional.ofNullable("juronging"), Optional.ofNullable(0)), (t1, t2) -> {
            Teacher teacher = new Teacher();
            int age = t1.getAge().get() + t2.getAge().get();
            teacher.setAge(Optional.of(age));
            teacher.setName(Optional.ofNullable("composer"));
            return teacher;
        });

        System.out.println(reduce);
    }

    @Test
    public void test02() {
        List<Teacher> teacherList = CollectionProviders.getProvider().getTeacherList();
        int reduce = teacherList.stream().mapToInt(t -> t.getAge().get()).reduce(0, (sum, intItem) -> {
            sum += intItem;
            return sum;
        });
        System.out.println("totalAge=    " + reduce);
    }

    @Test
    public void test03() {
        List<Teacher> teacherList = CollectionProviders.getProvider().getTeacherList();
        int sum = teacherList.stream().mapToInt(t -> t.getAge().get()).sum();


    }
}
