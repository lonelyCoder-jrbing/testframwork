package redis.redisdemo.distributelock2;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:zookeeper由于创建临时节点的方法时同步不方法
 **/
// 线程，尝试在zk上创建临时节点，创建成功则获得锁(执行权)
@Slf4j
class TestThread implements Runnable {
    // 共享变量
    private static Integer CNT = 0;
    private ZkClient zkClient;
    private CountDownLatch countDownLatch;
    public TestThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
 
    // 连接zk
    private void connect() {
        String threadName = Thread.currentThread().getName();
        try {
            System.out.println(threadName + " 等待执行...");
            // 等待倒计时结束
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " 请求连接zk..." + System.currentTimeMillis());
        zkClient = new ZkClient("localhost:2181", 20000);
        System.out.println(threadName + " 连接成功...");
        // 输出目录信息测试
//        List<String> children = zkClient.getChildren("/");
//        children.forEach(System.out::println);
    }
 
    @Override
    public void run() {
        // 初始化连接(在各个线程里开启连接，模拟分布式环境)
        connect();
        String threadName = Thread.currentThread().getName();
 
        // 竞争锁
        while (true) {
            try {
                System.out.println(threadName + " 开始竞争锁...");
                // 创建zk临时节点
                zkClient.createEphemeral("/dl", "test");
                log.info(threadName +" 获得锁！！！");
                // 获得锁后修改共享变量
                CNT ++;
                log.info(threadName + " 释放了锁...共享变量为={}", CNT);
                zkClient.delete("/dl");
                Thread.sleep(2000);
            } catch (Exception e) {
                // 创建临时节点失败，表示未获得锁
                System.out.println(threadName + " 未获得锁，将重试！！！");
//                System.out.println(e.getMessage());
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}