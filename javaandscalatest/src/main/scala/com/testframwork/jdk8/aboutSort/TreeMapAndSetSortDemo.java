package com.testframwork.jdk8.aboutSort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * create by sumerian on 2020/5/5
 * <p>
 * desc:
 **/
public class TreeMapAndSetSortDemo {
    public static void main(String[] args) {
        List<Students> collect = IntStream.range(0, 10).mapToObj(i -> new Students(i, i, i + "")).collect(Collectors.toList());
//        Map<String, Students> Students = new TreeMap((x,y)->x<y?1:-1);
        //treemap构造函数中的comparator是按照key进行排序的
        Map<Integer, Optional<Students>> studentMap = collect
                .stream()
                .collect(Collectors
                        .groupingBy(Students::getAge,
                                Collectors.reducing((x, y) -> x)));


        System.out.println("studentMap:  "+studentMap);



        TreeMap<Integer, Students> map = new TreeMap<>((x, y) -> x > y ? -1 : 1);

        studentMap.forEach((k, v) -> {
            map.put(k, v.get());
        });

        System.out.println(map);
        Map.Entry<Integer, Students> integerStudentsEntry = map.pollLastEntry();
        Students value = integerStudentsEntry.getValue();
        System.out.println("value:"+value);

    }


}
