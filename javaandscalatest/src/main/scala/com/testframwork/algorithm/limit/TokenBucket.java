package com.testframwork.algorithm.limit;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.time.Duration;

/**
 * create by sumerian on 2020/7/2
 * <p>
 * desc:Bandwidth：带宽，也就是每秒能够通过的流量，自动维护令牌生产。
 *
 * Bucket：桶，不论状态，或是令牌的消费，bucket是我们操作的入口。
 *
 * tryConsume：尝试消费n个令牌，返回布尔值，表示能够消费或者不能够消费，给我们判断依据。
 **/
public class TokenBucket {

    public static void main(String[] args) {
//        Bandwidth limit = Bandwidth.simple(10, Duration.ofSeconds(1));
//        CouchbaseProperties.Bucket bucket = Bucket4j.builder().addLimit(limit).build();
//        if(bucket.tryConsume(1)){
//            System.out.println("do something");
//        }else{
//            System.out.println("do nothing");
//        }
    }


}
