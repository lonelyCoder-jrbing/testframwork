package com.testframwork.threadtest.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/
public class Demo<T, Y, U, R> {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String str1 = "name";
        String str2 = "name";
        String str3 = "name";
        Future<String> submit = executorService.submit(() -> dosome(str1, str2, str3));
        String s = submit.get();
        System.out.println(s);
        executorService.shutdown();
    }

    public static String dosome(String t, String y, String u) {
        return get((g, h, j) -> getstr(g, h, j), t, y, u);
    }

    public static String getstr(String str1, String str2, String str3) {
        String s = "";
        if (str1.length() > 5) {
            s = String.valueOf(str1.hashCode());
        }
        String s1 = "";
        if (str2.length() > 5) {
            s1 = String.valueOf(str1.hashCode());
        }

        String s2 = "";
        if (str3.length() > 5) {
            s2 = String.valueOf(str1.hashCode());
        }

        return s + s1 + s2;
    }


    public static <String> String get(UDFun<String, String, String, String> udFun, String t, String y, String u) {
        return udFun.apply(t, y, u);
    }


    public interface UDFun<T, Y, U, R> {
        R apply(T t, Y y, U u);


    }


}
