package com.springdemo.ioctest.beanpostprocessorTest.annotationTest.test01;

import java.lang.reflect.Field;

public class Apple {
    @FruitName(value = "hongfushi")
    private String name;

    public static void print() {
        try {
            Class<?> aClass = Class.forName("com.springdemo.ioctest.beanpostprocessorTest.annotationTest.test01.Apple");
            Field name = aClass.getDeclaredField("name");
            FruitName fruitName = name.getAnnotation(FruitName.class);
            System.out.println("fruitName:  " + fruitName.value());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Apple apple = new Apple();

        print();

    }
}
