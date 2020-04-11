package redis.redisdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * https://segmentfault.com/a/1190000013333723
 * 管道处理实例
 *
 * @author huangy on 2018/8/5
 * 所有的请求合并为一次IO，除了时延可以降低之外，还能大幅度提升系统吞吐量
 */

/***
 *  上述实例使用50个线程进行处理，每个线程分别处理了1000个redis命令（set），在不使用管道的情况下，
 *  耗时大约5秒；使用管道的情况下，耗时大约2秒。
 * 3、缺点：
 * （1）pipeline机制可以优化吞吐量，但无法提供原子性/事务保障，而这个可以通过Redis-Multi等命令实现。
 * （2）部分读写操作存在相关依赖，无法使用pipeline实现
 */


public class Piple {

    private static Logger logger = LoggerFactory.getLogger(Piple.class);

    private static JedisPool jedisPool;

    // 总共有多少个并发任务
    private static final int taskCount = 50;
    // 管道大小
    private static final int batchSize = 10;
    // 每个任务处理的命令数
    private static final int cmdCount = 1000;
    // redis服务地址
    private static final String host = "127.0.0.1";
    // 端口
    private static final int port = 6379;
    // 是否使用管道
    private static final boolean usePipeline = true;
    // 保存执行结果
    private static List<Object> results = new ArrayList<>(cmdCount);

    private static void init() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(100);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        jedisPool = new JedisPool(poolConfig, host, port);
    }

    public static void main(String[] args) throws InterruptedException {
        // 初始化redis
        init();

        long beginTime = System.currentTimeMillis();

        // 使用多线程执行
        ExecutorService executor = Executors.newCachedThreadPool();

        // 使用CountDownLatch保证所有任务都执行完
        CountDownLatch latch = new CountDownLatch(taskCount);
        for (int i = 0; i < taskCount; i++) {
            executor.submit(new DemoTask(i, latch));
        }

        latch.await();
        executor.shutdownNow();

        long endTime = System.currentTimeMillis();

        // 耗时
        logger.info("execution cost time(s)={}", (endTime - beginTime) / 1000.0);

        // 所有结果
        logger.info("results, size={}, all={}", results.size(), results);
    }

    private static Jedis get() {
        return jedisPool.getResource();
    }

    private static class DemoTask implements Runnable {

        private int id;
        private CountDownLatch latch;

        private DemoTask(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            logger.info("Task[{}] start.", id);
            try {
                if (usePipeline) {
                    runWithPipeline();
                } else {
                    runWithNonPipeline();
                }
            } finally {
                latch.countDown();
            }

            logger.info("Task[{}] end.", id);
        }

        /**
         * 不使用管道处理redis命令
         */
        private void runWithNonPipeline() {
            // 总共处理cmdCount个
            for (int i = 0; i < cmdCount; i++) {
                Jedis jedis = get();
                try {
                    jedis.set(key(i), UUID.randomUUID().toString());
                } finally {
                    if (jedis != null) {
                        jedisPool.returnResource(jedis);
                    }
                }
                if (i % batchSize == 0) {
                    logger.info("Task[{}] process -- {}", id, i);
                }
            }
        }

        /**
         * 使用管道处理redis命令
         * 总共处理cmdCount个redis命令，每次处理batchSize个
         */
        private void runWithPipeline() {

            for (int i = 0; i < cmdCount; ) {
                Jedis jedis = get();

                try {
                    Pipeline pipeline = jedis.pipelined();
                    int j;
                    for (j = 0; j < batchSize; j++) {
                        if (i + j < cmdCount) {
                            pipeline.set(key(i + j), UUID.randomUUID().toString());
                        } else {
                            break;
                        }
                    }
//                    pipeline.sync();

                    // 查看结果，使用syncAndReturnAll
                    List<Object> tem = pipeline.syncAndReturnAll();
                    logger.info("Task thread id={} pipeline, cmd end={}, result size={}", id, i + j, tem.size());

                    results.addAll(tem);

                    i += j;

                } finally {
                    if (jedis != null) {
                        jedisPool.returnResource(jedis);
                    }
                }

            }
        }

        private String key(int i) {
            return i + "";
        }
    }

}