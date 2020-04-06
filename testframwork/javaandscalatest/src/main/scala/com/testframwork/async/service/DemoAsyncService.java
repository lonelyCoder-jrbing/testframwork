package com.testframwork.async.service;

import java.util.concurrent.Future;

public interface DemoAsyncService {
    Future<String> doTaskOne() throws Exception;

    Future<String> doTaskTwo() throws Exception;

    Future<String> doTaskThree() throws Exception;
}
