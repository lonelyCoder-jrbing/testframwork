package com.testframwork.jsontest;

public class Demo2 {

    public static void main(String[] args) {
        boolean flag = true;
        for (int i = 0; i < 10; i++) {
            for (int j = flag ? 1 : 0; j < 5; j++) {
                System.out.println(i + "=====" + j);
            }
        }


    }


}
