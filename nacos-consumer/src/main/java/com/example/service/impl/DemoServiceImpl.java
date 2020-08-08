package com.example.service.impl;

import com.example.service.DemoService;
import com.example.service.api.ITestNacos;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * create by sumerian on 2020/8/6
 * <p>
 * desc:
 **/
@Service
public class DemoServiceImpl implements DemoService {


    @Reference(group = "test-nacos2",
//            check = true ,
            version = "${dubbo.application.version}",  //多版本(灰色发布)
            timeout = 3000,    //当消费者调用提供者时由于网络等原因有可能会造成长时间拿不到响应,而请求还在不断的发过来这就有可能造成线程阻塞,使用timeout设置超时时间当超过该时间就会抛出异常
            retries = 3,    //当调用失败或超时后重新尝试调用的次数,其值不包含第一次
            mock = "false",
            async = true,
            parameters = {"getOrderFormat.mock", "com.example.service.MockService"}

    )
    ITestNacos iTestNacos;


    @HystrixCommand(fallbackMethod = "handlerException")
    @Override
    public String getOrderFormat() throws ExecutionException, InterruptedException {
        String orderFormat = iTestNacos.getOrderFormat(1);
        //客户端不需要启动多线程即可完成并行调用多个远程服务，相对多线程开销较小，异步调用会返回一个 Future 对象。
        Future<String> future = RpcContext.getContext().getFuture();
        return future.get();
    }

    public String handlerException() {
        return "jurongbing";
    }


}
