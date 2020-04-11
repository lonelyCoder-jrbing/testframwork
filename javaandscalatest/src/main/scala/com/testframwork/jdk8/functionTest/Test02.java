package com.testframwork.jdk8.functionTest;

import java.math.BigInteger;

public class Test02 implements MyInterface {


    @Override
    public String getName() {
        String dosomething = dosomething(name);
        char[] chars = dosomething.toCharArray();


        return dosomething;
    }

    public static void main(String[] args) {
        int i = myAtoi("2147483648");
        System.out.println(i);

    }

    public static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        if (str.equals("")) {
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        for (char o : chars) {
            if (!Character.isDigit(o)) {
                return 0;
            }
            if (o == '-') {
                if (!sb.toString().contains("-")) {
                    sb.append(o);
                }

            }
            if (Character.isDigit(o)) {
                sb.append(o);
            }
        }

        if (new BigInteger(sb.toString(), 10).subtract(new BigInteger(String.valueOf(Integer.MAX_VALUE), 10)).longValue() < 0l || Long.valueOf(sb.toString()) < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return Integer.valueOf(sb.toString());

        }
    }
}
