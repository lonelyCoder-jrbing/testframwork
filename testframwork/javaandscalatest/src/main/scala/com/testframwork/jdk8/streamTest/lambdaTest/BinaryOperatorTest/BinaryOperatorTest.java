package com.testframwork.jdk8.streamTest.lambdaTest.BinaryOperatorTest;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {
    public static void main(String[] args) {
        //进行的是聚合操作
        BinaryOperatorTest  test = new  BinaryOperatorTest();
        test.testBinaryOperator(1,2,(nums1,nums2)->nums1+nums2);
        test.testBinaryOperator(4,2,(num1,num2)->num1 - num2);
        test.testBinaryOperator(3,2,(num1,num2)->num1 * num2);
        test.testBinaryOperator(8,2,(num1,num2)->num1 / num2);
        //
        test.testMinBy("hello","wonders",(str1,str2)->str1.length()-str2.length());
        //方法引用
        test.testMinBy("hello","wonders", Comparator.comparingInt(String::length));



    }

    public void testBinaryOperator(Integer num1, Integer num2, BinaryOperator<Integer> result) {
        System.out.println(result.apply(num1, num2));
    }

    public void testMinBy(String str1, String str2, Comparator<String> comparator) {
        System.out.println(BinaryOperator.minBy(comparator).apply(str1, str2));
    }
    public void testMaxBy(String str1, String str2, Comparator<String> comparator){
        System.out.println(BinaryOperator.maxBy(comparator).apply(str1,str2));
    }

}
