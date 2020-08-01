package redis.redisdemo.distributelock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.redisdemo.distributelock.annotation.RedisLock;
import redis.redisdemo.distributelock.annotation.RedisLockKey;
import redis.redisdemo.distributelock.enums.RedisLockKeyType;
import redis.redisdemo.distributelock.service.DistributeLockService2;

import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:
 **/
@Service
@Slf4j
public class DistributeLockServiceImpl2 implements DistributeLockService2 {
    @Override
    @RedisLock(lockKey = "lockKey", expireTime = 100, retryCount = 3)
    public String method2(@RedisLockKey(type = RedisLockKeyType.ALL) String num) throws InterruptedException {
        int sleepMs = 1000;
        TimeUnit.MILLISECONDS.sleep(sleepMs);
        log.info("修改共享变量num");
        log.info("method1 .....休眠=={},   num=={}", sleepMs, num);
        return "method2";
    }
}
