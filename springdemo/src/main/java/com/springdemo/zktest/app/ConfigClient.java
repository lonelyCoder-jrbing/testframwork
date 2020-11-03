package com.springdemo.zktest.app;

import com.springdemo.zktest.config.ZkConfigClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConfigClient {
    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(ZookeeperApiDemoApplication.class, args);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 模拟多个客户端获取配置
        executorService.submit(new ZkConfigClient());
        executorService.submit(new ZkConfigClient());
        executorService.submit(new ZkConfigClient());
    }


}
