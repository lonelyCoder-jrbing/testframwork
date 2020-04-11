package com.testframwork.jdk8.jdk8Thingking.functionalYTest;

public class LambdaExpressions {
    //相当于是借口的一种实现方法
    static Body body1 = h -> h + "no parents";
    static Body body2 = (h) -> h + "more details";

    static Description des = () -> "short info";
    static Multi multi = (m, n) -> m + n;
    static Description moreLines = () -> {
        System.out.println("moreLines:   ");
        return "from moreLines";
    };

    public static void main(String[] args) {
        String param1 = body1.detailed("param1");
        System.out.println("test1: " + param1);
        String param2 = body2.detailed("param2");
        System.out.println("test2:   " + param2);
        String breif = moreLines.breif();
        System.out.println("breif:   " + breif);


    }


}
