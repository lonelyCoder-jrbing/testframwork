package com.testframwork.threadtest;
import java.util.concurrent.*;

/**
 * create by sumerian on 2020/4/17
 * <p>
 * desc:测试java中的callable   callable 与  runnable
 * callable   具有返回值
 * runnable   没有返回值
 **/
public class CallableTest {

    public static void main(String[] args) {

        ExecutorService service = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Future<String> futureSubmit01 = service.submit(() -> {
            return "str1";
        });
        Future<String> futureSubmit02 = service.submit(() -> {
            return "str2";
        });

        String s1 = "";
        String s2 = "";
        try {
            s1 = futureSubmit01.get();
            s2 = futureSubmit02.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);

    }


}
