package com.springdemo.beanpostprocessorTest.service.impl;

import com.springdemo.beanpostprocessorTest.utils.FileToWords;
import com.springdemo.beanpostprocessorTest.service.CalculateService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


@Service("calculateService")
public class CaculateserviceImpl implements CalculateService {

    private String serviceDesc = "desc from class";


    @Override
    public int add(int a, int b) {
        return a + b;

    }

    @Override
    public String getServiceDesc() {
        return this.serviceDesc;
    }

    @Override
    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    @Override
    public Map<String, Integer> getWordCountFromLogFile() throws IOException {
        return FileToWords.stream("D:\\testframwork\\words")
                .collect(Collectors.groupingBy(e -> e.toLowerCase(),
                        Collectors.summingInt(p -> 1)));
    }


}
