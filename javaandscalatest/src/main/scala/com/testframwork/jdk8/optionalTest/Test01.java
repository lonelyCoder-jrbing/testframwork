package com.testframwork.jdk8.optionalTest;

import java.util.Optional;

public class Test01 {

    public static void main(String[] args) {
        Student s = new Student();
        s.setAge(Optional.of(12));
        s.setName(Optional.of("jrbing"));
        Student s2 = null;
        Optional<Student> s21 = Optional.ofNullable(s2);
        //Optional<Student> s1 = Optional.of(s);
        Optional<Student> s1 = Optional.ofNullable(s);
        System.out.println("s1:    " + s1.get());
        System.out.println(s1.isPresent());
        System.out.println(s21.orElse(s));

        System.out.println(s21.orElseGet(() -> new Student()));
        System.out.println("=====================");
//        System.out.println(s21.orElseThrow(()->new RuntimeException()));

    }


}
