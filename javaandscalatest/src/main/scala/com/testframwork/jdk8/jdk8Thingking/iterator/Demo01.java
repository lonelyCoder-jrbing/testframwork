package com.testframwork.jdk8.jdk8Thingking.iterator;


import com.alibaba.nacos.client.config.impl.ClientWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * create by sumerian on 2020/4/20
 * <p>
 * desc:
 **/
public class Demo01 {

    public static void main(String[] args) {


    }

    static class ServerAddressIterator implements Iterator<String> {
        String serverIp = "";

        public ServerAddressIterator() {
        }

        public ServerAddressIterator(String serverIp) {
            this.serverIp = serverIp;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer<? super String> action) {

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public String next() {
            return null;
        }
    }
}
