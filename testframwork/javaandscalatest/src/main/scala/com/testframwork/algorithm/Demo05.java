package com.testframwork.algorithm;

import org.junit.Test;

import java.net.URLStreamHandler;

public class Demo05 {

    public static void main(String[] args) {
        String moves = "UD";
        Demo05 demo05 = new Demo05();
        boolean b = demo05.judgeCircle(moves);
        System.out.println("result: " + b);
    }

    public boolean judgeCircle(String moves) {
        int numsU = 0;
        int numsD = 0;
        int numsL = 0;
        int numsR = 0;

        for (int i = 0; i < moves.length(); i++) {
            Character charAt = (Character) moves.charAt(i);
            if (charAt.equals('U')) {
                numsU = numsU + (-1);
            }
            if (charAt.equals('D')) {
                numsD = numsD + 1;
            }
            if (charAt.equals('R')) {
                numsR = numsR + 1;
            }
            if (charAt.equals('L')) {
                numsL = numsL + (-1);
            }
        }
        return numsL + numsR + numsD + numsU == 0 ? true : false;
    }

}
