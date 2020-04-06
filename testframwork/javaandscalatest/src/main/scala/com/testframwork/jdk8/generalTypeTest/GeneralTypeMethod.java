package com.testframwork.jdk8.generalTypeTest;

import java.util.ArrayList;
import java.util.List;


public class GeneralTypeMethod<T> {

    public static void main(String[] args) {
        List<Integer> lsit = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lsit.add(Integer.valueOf("1223"));
        }
        List<List<Integer>> partition = GeneralTypeMethod.<Integer>partition(lsit, 5);
        System.out.println("partition:  " + partition);
        List<Integer> integers = partition.get(0);
        System.out.println("integer::    " + integers);

    }

    /*泛型方法，表方法中的参数列表中的类型还有返回值中的类型必须和该方法中给出的类型保持一致。*/
    public static <T> List<List<T>> partition(List<T> list, int size) {
        if (list == null) {
            throw new NullPointerException("List must not be null");
        } else if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        } else {
            return new ListUtils.Partition<T>(list, size);
        }
    }
}
