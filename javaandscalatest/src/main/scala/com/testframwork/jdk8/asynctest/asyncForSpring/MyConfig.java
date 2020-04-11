package com.testframwork.jdk8.asynctest.asyncForSpring;


import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
public class MyConfig {

    @Bean
    @Deprecated
    public TaskExecutor executor() {
        ThreadPoolTaskExecutor executors = new ThreadPoolTaskExecutor();
        executors.setCorePoolSize(10);//设置核心线程数量
        executors.setMaxPoolSize(20);//最大线程数量
        executors.setQueueCapacity(100);
        executors.setKeepAliveSeconds(200);
        executors.setThreadNamePrefix("fsx-Executor-");//指定用于新创建的线程名称的前缀
//  executors.setRejectedExecutionHandler(new  ThreadPoolTaskExecutor.CallerRunsPolicy());
        return executors;
    }

}
