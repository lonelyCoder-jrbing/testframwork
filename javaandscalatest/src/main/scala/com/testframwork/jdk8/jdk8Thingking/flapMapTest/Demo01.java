package com.testframwork.jdk8.jdk8Thingking.flapMapTest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo01 {
    //hashmap的values为map的值的集合.
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> collect = FileToWords.stream("words").collect(Collectors.groupingBy(Function.identity()));
        Iterator<List<String>> it = collect.values().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }


}
