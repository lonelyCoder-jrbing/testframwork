package com.testframwork.jdk8.jdk8Thingking.concurrent;

public enum Demo01 {
    ZHANG(1, "zhangmeiyan"),
    WANG(2, "wangwu"),
    LI(3, "lida");
    private int code;
    private String name;

    Demo01(int code, String name) {
        this.code = code;
        this.name = name;
    }

    Demo01 step() {
        if (equals(LI))
            return LI;
        return values()[ordinal() + 1];
    }

    public static void main(String[] args) {
        Demo01 step = Demo01.WANG.step();
        System.out.println(step);

    }

}
