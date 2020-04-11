package com.testframwork.jdk8.optionalTest;

import java.util.Optional;

public class Test02 {

    public static void main(String[] args) {
//    Student student = new Student();
//    student.setAge(Optional.of(12));
//    student.setName(Optional.of("jrbing"));
//    String name = getName(student);
//    System.out.println(name);
//    System.out.println("==============================");
//    Student s = null;
//    Optional<Student> s1  = Optional.ofNullable(s);
//    Studentsss s2 = new Studentsss();
//    System.out.println(s1.orElseGet((Supplier<? extends Student>) s2));
        Student s = null;
        Optional<Student> s1 = Optional.ofNullable(s);
        boolean present = s1.isPresent();
        System.out.println("is present: " + present);

        //此两种方法是非常重要方法。
        Student student = Optional.ofNullable(s).orElse(createStudent("jrbing", 19));
        Student student2 = Optional.ofNullable(s).orElseGet(() -> createStudent("byy", 18));

        System.out.println(student);
        System.out.println("===================");
        System.out.println(student2);

    }

    private static Student createStudent(String name, int age) {
        Student student = new Student();
        student.setName(Optional.ofNullable(name));
        student.setAge(Optional.ofNullable(age));
        return student;
    }


}
