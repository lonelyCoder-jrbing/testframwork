package com.testframwork.algorithm.cache;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
public class App {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        Object old = objectObjectConcurrentHashMap.put("name", "jurongbing");
        //concurrentHashMap如果key值在map中存在的话，会返回旧值回来。
        Object old1 = objectObjectConcurrentHashMap.put("name", "jurongbing");
        System.out.println("old: "+old1);



        LocalCache localCache = new LocalCache();
        localCache.set("name", "jurongbing", 5000);
        localCache.set("name1", "boyinyue", 2000);
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            Object name = localCache.get("name");
            System.out.println("name:  "+name);
            Object name1 = localCache.get("name1");
            System.out.println("name:  "+name1);
        }


    }

}
