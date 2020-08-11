package com.testframwork.jdk8.mapTest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

//hashmap 在key保存integer，值比较下的情况之下，迭代输出有序，但是在key比较大的情况之下，迭代输出无序
public class PutifAbsentTest01 {


    public static void main(String[] args) {

        Set hashSet = new HashSet();
        hashSet.add(4);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(70);
        hashSet.forEach(PutifAbsentTest01::print);
        Iterator iterator = hashSet.iterator();
        for (int i = 0; iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }
        Map<Integer, Object> map = new ConcurrentHashMap<>();
        map.put(110032, "jjj");
        map.put(151000, "jjj");
        map.put(121000000, "jjj");
        Set<Map.Entry<Integer, Object>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Object>> iterator1 = entries.iterator();
        for (;iterator1.hasNext(); ) {
            Map.Entry<Integer, Object> next = iterator1.next();
            Integer key = next.getKey();
            Object value = next.getValue();
            System.out.println("key:  "+key+"   value:  "+value);
        }


        Map<Integer, Object> map2 = new ConcurrentHashMap<>();
        map2.put(2, "jjj");
        map2.put(3, "jjj");
        map2.put(1, "jjj");
        Set<Map.Entry<Integer, Object>> entries1 = map2.entrySet();
        Iterator<Map.Entry<Integer, Object>> iterator2 = entries1.iterator();
        for (;iterator2.hasNext(); ) {
            Map.Entry<Integer, Object> next = iterator2.next();
            Integer key = next.getKey();
            Object value = next.getValue();
            System.out.println("key1:  "+key+"   value1:  "+value);
        }
        Hashtable <String,Object>hashtable = new Hashtable(16,0.75f);
        hashtable.put("name","jurongbing");
        Iterator<Map.Entry<String, Object>> iterator3 = hashtable.entrySet().iterator();
       for(;iterator3.hasNext();){
            hashtable.put("name1","jurongbing01");
           Map.Entry<String, Object> next = iterator3.next();
           String key = next.getKey();
           Object value = next.getValue();
           System.out.println("key1:  "+key+"   value1:  "+value);

       }
    }
    public static void print(Object str) {
        System.out.println("str:   " + str);
    }


}
