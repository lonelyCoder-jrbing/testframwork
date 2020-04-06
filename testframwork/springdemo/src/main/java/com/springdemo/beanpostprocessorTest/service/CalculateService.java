package com.springdemo.beanpostprocessorTest.service;

import java.io.IOException;
import java.util.Map;

public interface CalculateService {
    /**
     * 整数加法
     *
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b);

    /**
     * 返回当前实现类的描述信息
     *
     * @return
     */
    String getServiceDesc();

    /**
     * 设置当前实现类的描述信息
     *
     * @return
     */
    void setServiceDesc(String serviceDesc);


    Map<String,Integer> getWordCountFromLogFile() throws IOException;


}
