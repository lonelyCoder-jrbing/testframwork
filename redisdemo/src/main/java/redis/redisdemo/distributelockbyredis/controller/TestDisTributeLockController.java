package redis.redisdemo.distributelockbyredis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.redisdemo.distributelockbyredis.service.DistributeLockService;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:基于注解的redis的分布式锁实现
 **/
@RestController
@RequestMapping("/distribute")
@Slf4j
public class TestDisTributeLockController {

    @Autowired
    private DistributeLockService distributeLockService;


    @RequestMapping("test/{abc}/bcd")
    @ResponseBody
    public String test01(@PathVariable("abc") String abc) throws InterruptedException {
        log.info("收到请求：{}", abc);
        distributeLockService.method1(abc);
        return "ok";
    }


}
