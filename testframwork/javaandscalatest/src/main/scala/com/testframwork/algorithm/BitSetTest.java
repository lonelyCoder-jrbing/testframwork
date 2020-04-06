package com.testframwork.algorithm;

import java.util.BitSet;
import java.util.Random;

public class BitSetTest {


    public static void main(String[] args) {
        Random random = new Random();
        byte bt = (byte) random.nextInt();
        BitSet bb = new BitSet();
        for (int i = 0; i >= 0; i--)
            if (((1 << i) & bt) != 0)
                bb.set(i);
            else
                bb.clear(i);
        System.out.println("byte value : " + bt);
        System.out.println("bitSet :" + bb);


    }


}
