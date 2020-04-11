package com.testframwork.jdk8.jdk8Thingking.Usablefunctions;

public class Looping {

    static void hi() {
        System.out.println("Hi");
    }

    public static void main(String[] args) {
        Repeat.repeat(3, () -> System.out.println("looping...."));
        Repeat.repeat(2, Looping::hi);

    }

}
