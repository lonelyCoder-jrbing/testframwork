package com.testframwork.jdk8.streamTest.lambdaTest.buildPojo;

public class Student {

    private String name;
    private int age;

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public Student age(int age) {
        this.age = age;
        return this;
    }

    public static Student builder() {
        return new Student();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
