package com.testframwork.jdk8.jdk8Thingking.MethodReferences;


import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

//回调函数  ,注意一下
// [2] show() 的签名（参数类型和返回类型）符合 Callable 的 call() 的签名
public class MethodReferences {
    static void hello(String name) { // [3]
        System.out.println("Hello, " + name);
    }


    static class Description {
        String about;

        Description(String desc) {
            about = desc;
        }

        void help(String msg) { // [4]
            System.out.println(about + " " + msg);
        }
    }

    static class Helper {
        static void assist(String msg) { // [5]
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Describe d = new Describe();
        BiConsumer<Describe, String> show = Describe::show;
        Consumer<String> show1 = d::show;
        show1.accept(String.format("%s,test consumer", "test"));


        List<String> integers = Arrays.asList("1", "2", "3", "4", "5");


        System.out.println("================consumer test===============");

        integers.stream().forEach(d::show);

        System.out.println("================consumer test===============");
        Callable c = d::show; // [6]


        c.call("call()"); // [7]

        c = MethodReferences::hello; // [8]
        c.call("Bob");

        c = new Description("valuable")::help; // [9]
        c.call("information");

        c = Helper::assist; // [10]
        c.call("Help!");
    }
}