package redis.redisdemo.distributelock2;

import org.I0Itec.zkclient.ZkClient;
 
import java.util.concurrent.CountDownLatch;
/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:基于zookeeper的分布式锁实现
 **/
public class ZKDistributeLockTest {
 
    public static void main(String[] args) {
        // 使用CountDownLunch控制线程同时执行
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 开启3个线程模拟分布式环境，分布式环境下每个进程都是一个单独的zkClient
        Thread t1 = new Thread(new TestThread(countDownLatch));
        Thread t2 = new Thread(new TestThread(countDownLatch));
        Thread t3 = new Thread(new TestThread(countDownLatch));
        t1.start();
        t2.start();
        t3.start();
 
        System.out.println("休眠1秒后执行..." + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 倒计时结束
        countDownLatch.countDown();
    }
 
 
}