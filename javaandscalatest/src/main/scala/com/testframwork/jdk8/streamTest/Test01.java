package com.testframwork.jdk8.streamTest;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test01 {


    public static void main(String[] args) {
        String[] str = {"jrbing001", "jrbing002"};
        List<Student> students = Lists.newArrayList();
        Student student = new Student();
        student.setName("第一位");
        student.setNumber(1);
        students.add(student);
        Student student1 = new Student();
        student1.setNumber(2);
        student1.setName("第二位");
        students.add(student1);

        Student student2 = new Student();
        student2.setNumber(2);
        student2.setName("第仨位");
        students.add(student2);
        students = students.subList(0,2);
        System.out.println(students);
/*
    Map<Integer, Student> studentMap = students.stream()
        .collect(Collectors.toMap(Student::getNumber, Function.identity()));
    System.out.println(studentMap);
    Map<Integer,Student> studentMap1 = new HashMap<>();
    students.forEach(a->studentMap1.put(a.getNumber(),a));
    System.out.println(studentMap1);
  */
        //对于重复key的情况
//    Map<Integer, Student> collect = students.stream()
//        .collect(Collectors.toMap(Student::getNumber, Function.identity()));
//    System.out.println("collect:   "+collect);

   /* Map<Integer, Student> collect = students.stream()
        .collect(Collectors.toMap(Student::getNumber, Function.identity(), (key1, key2) -> key2));
*/
        Map<Integer, Student> collect = students.stream()
                .collect(Collectors.toMap(Student::getNumber, s -> s, (key1, key2) -> key1));
        System.out.println("collect:  " + collect);
        //重载方法
        Map<Integer, Student> collect1 = students.stream()
                .collect(
                        Collectors.toMap(Student::getNumber, s -> s, (key1, key2) -> key1, LinkedHashMap::new));
        System.out.println("collect:  " + collect1);
        //还有一种分组的方法
        Map<Integer, List<Student>> collect2 = students.stream()
                .collect(Collectors.groupingBy(Student::getNumber));
        System.out.println("collect2:  " + collect2);
    }

    public  List<Student> getStudent() {
        ArrayList<Student> students = Lists.newArrayList();
        Student student = new Student();
        student.setName("第一位");
        student.setNumber(1);
        students.add(student);
        Student student1 = new Student();
        student1.setNumber(2);
        student1.setName("第二位");
        students.add(student1);

        Student student2 = new Student();
        student2.setNumber(2);
        student2.setName("第仨位");
        students.add(student2);
        return students;
    }

}
