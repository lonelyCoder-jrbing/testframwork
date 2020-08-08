package com.example.service;

import java.util.concurrent.ExecutionException;

/**
 * create by sumerian on 2020/8/6
 * <p>
 * desc:
 **/
public interface DemoService {

    String getOrderFormat() throws ExecutionException, InterruptedException;

}
