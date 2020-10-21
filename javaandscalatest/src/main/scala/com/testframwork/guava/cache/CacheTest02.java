package com.testframwork.guava.cache;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.tools.ant.Executor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class CacheTest02 {

    static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {

        Cache<String, Object> manualCache = Caffeine.newBuilder().executor(executorService)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        executorService.scheduleAtFixedRate(() -> {
            //每隔5秒钟，将缓存中的数据清空，然后放入最新的值进去。
            manualCache.invalidateAll();
            Object name1 = manualCache.get("name1", (e) -> "jurongbing");
            System.out.println("name1:  " + name1);
        }, 0L, 5L, TimeUnit.SECONDS);
        String key = "name1";
        // 根据key查询一个缓存，如果没有返回NULL
        Object ifPresent = manualCache.getIfPresent(key);
        // 根据Key查询一个缓存，如果没有调用createExpensiveGraph方法，并将返回值保存到缓存。
        // 如果该方法返回Null则manualCache.get返回null，如果该方法抛出异常则manualCache.get抛出异常
        String o = (String) manualCache.get(key, k -> createExpensiveGraph(k));
        System.out.println("o: " + o);
        ConcurrentMap<String, Object> stringObjectConcurrentMap = manualCache.asMap();
        System.out.println(stringObjectConcurrentMap);
        // 将一个值放入缓存，如果以前有值就覆盖以前的值
        manualCache.put(key, o);

        // 删除一个缓存
//        manualCache.invalidate(key);
        Object name = manualCache.get("name1", k -> null);
        System.out.println("name:  " + name);


        ConcurrentMap<String, Object> map = manualCache.asMap();

    }

    interface func1 {
        String apply(String in);

    }

    public static String createExpensiveGraph(String in) {
        return in + "woshicache";

    }

}
