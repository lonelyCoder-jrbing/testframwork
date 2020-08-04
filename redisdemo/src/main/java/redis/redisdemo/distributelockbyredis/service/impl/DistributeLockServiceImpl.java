package redis.redisdemo.distributelockbyredis.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.redisdemo.distributelockbyredis.annotation.RedisLock;
import redis.redisdemo.distributelockbyredis.annotation.RedisLockKey;
import redis.redisdemo.distributelockbyredis.enums.RedisLockKeyType;
import redis.redisdemo.distributelockbyredis.service.DistributeLockService;
import redis.redisdemo.distributelockbyredis.service.DistributeLockService2;

import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:
 **/
@Service
@Slf4j
public class DistributeLockServiceImpl implements DistributeLockService {

    //多个机器的jvm共同操作的变量,也是多个线程操作的变量
    private int shareVarible;

    @Autowired
    private DistributeLockService2 distributeLockService2;


    @Override
    @RedisLock(lockKey = "lockKey", retryCount = 50, expireTime = 100)
    public String method1(@RedisLockKey(type = RedisLockKeyType.ALL) String num) throws InterruptedException {
        int sleepMs = 3000;
        TimeUnit.MILLISECONDS.sleep(sleepMs);
        log.info("修改共享变量num");
        shareVarible = shareVarible + 10;
        log.info("method1 .....休眠=={},   shareVarible=={}",sleepMs,num);
        String s = distributeLockService2.method2(num);
         return "method1"+shareVarible;

    }
}
