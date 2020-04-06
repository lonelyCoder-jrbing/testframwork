package com.testframwork.jdk8.functionTest;

public interface MyInterface {

    static final String name = Name.BYY.getEnName();

//    static void test() {
//        MyFunction MyFunction = new MyFunction();
//
////    String  jrbing =(String) MyFunction.apply("jrbing");
////    System.out.println(jrbing);
////    System.out.println("jrbing"+jrbing);
//    }

//    public static void main(String[] args) {
////        test();
//
//    }

    default String dosomething(String name) {
        System.out.println("hello everyone");
        return name;
    }

    String getName();
}
