package com.testframwork.vm.finalizeTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.json.JsonObject;
import java.util.Map;

public class Demo01 {

    @Data
    static class Apple{
        private String id;
        private String name;
    }


    public static void main(String[] args) throws Throwable {
        Apple apple = new Apple();
        apple.setId("123");
        apple.setName("name");
        Map map = JSONObject.parseObject(JSON.toJSONString(apple), Map.class);
        System.out.println(map);
    }

    //另外，重写finalize()方法意味着延长了回收对象时需要进行更多的操作，从而延长了对象回收的时间。
    @Override
    protected void finalize() throws Throwable {
        System.out.println("调用finalize方法");

    }
}
