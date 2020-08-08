package com.testframwork.threadtest.threadpool;

import java.util.concurrent.*;

/**
 * create by sumerian on 2020/8/7
 * <p>
 * desc:   线程中处理异常的方式
 **/
public class ExceptionHandleDemo {


    public static void main(String[] args) {

//       demo01();
//        demo02();
        demo03();
    }

    /****
     * 通过future 对象的get方法进行处理异常
     * @return
     */
    public static String demo01() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Future<String> submit = service.submit(() -> {
                System.out.println("current thread name: " + Thread.currentThread().getName());
                Object object = null;
                System.out.println(object.toString());
                return "jurongbing";
            });
            try {
                String s = submit.get();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException：    " + e);
            } catch (ExecutionException e) {
                System.out.println("ExecutionException:    " + e);
            }


        }
        return "";
    }

    /**
     * 在创建的任务内部进行 try catch
     *
     * @return
     */
    public static String demo02() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service.submit(() -> {
                System.out.println("current thread name: " + Thread.currentThread().getName());
                try {
                    Object object = null;
                    System.out.println(object.toString());
                } catch (Exception e) {
                    System.out.println("Exception：   {}" + e);
                }

                return Thread.currentThread().getName();
            });
        }

        return "";
    }

    public static String demo03() {
        ExecutorService service = Executors.newFixedThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(
                    (t1, e) -> {
                        System.out.println(t1.getName() + "线程抛出的异常" + e);
                    });
            return t;
        });

        service.execute(() -> {
            Object object = null;
            System.out.println(object.toString());
        });
        return "";
    }

}
