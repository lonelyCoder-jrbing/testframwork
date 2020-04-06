package com.testframwork.jdk8.jdk8Thingking.MethodReferences;

class Describe {
    void show(String msg) {
        String s = "test consumer" + msg;

        // [2]
        System.out.println(s);
    }
}