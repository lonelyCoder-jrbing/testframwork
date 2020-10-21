package com.testframwork.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CacheTest {

    public static void main(String[] args) {
      Cache<String, String> build = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.DAYS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        //数据库查寻的逻辑
                        return "byy";
                    }
                });
        build.put("name","jurongbing");
//        build.get("name",()->{})
        ConcurrentMap<String, String> stringStringConcurrentMap = build.asMap();
        System.out.println("stringStringConcurrentMap:"+stringStringConcurrentMap);



    }

}
