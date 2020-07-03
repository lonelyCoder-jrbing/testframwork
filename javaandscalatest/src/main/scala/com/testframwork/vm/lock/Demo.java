package com.testframwork.vm.lock;

/**
 * create by sumerian on 2020/7/1
 * <p>
 * desc: 测试volitile关键字。volitail不能保证原子性。
 **/
public class Demo {
    public static volatile int race = 0;

    //synchronized可以保证原子性
    public static synchronized void increase() {
        race++;
    }
    //如果不添加synchronized关键字，则会出现线程不安全的问题。
/*    public static  void increase() {
        race++;
    }*/
    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }

}
