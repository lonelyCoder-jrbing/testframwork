package com.testframwork.threadtest.threadpool;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.index.qual.NonNegative;

import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/9/3
 * <p>
 * desc:
 **/
public class Demo01 {

    public static void main(String[] args) {

//        Cache<Object, String> cache = Caffeine.newBuilder()
//                .maximumSize(10_000)
//                .expireAfterWrite(5, TimeUnit.MINUTES)
//                .refreshAfterWrite(1, TimeUnit.MINUTES)
//                .build(key -> "name");
//        String name = cache.get("name", v -> "juronging");
//        String name1 = cache.getIfPresent("name");
//        System.out.println("name1: "+name1);
//        System.out.println("name:  "+name);
//        cache.invalidateAll();
//        @NonNegative long l = cache.estimatedSize();
//        System.out.println("l:  "+l);


        String path = "D:\\project\\skyscraper-engine-service\\skyscraper-engine\\engine-web\\src\\main\\resources\\zanzuo";
        String paperName = path.split("/")[path.split("").length - 1];

        System.out.println("paperName:  "+paperName);
    }



}
