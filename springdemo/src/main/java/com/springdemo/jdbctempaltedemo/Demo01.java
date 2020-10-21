package com.springdemo.jdbctempaltedemo;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class Demo01 {
    @Data
    public static class Demo02 {
        private BigDecimal main;

    }

    @Data
    public static class Demo03 {
        private Boolean main;

    }

    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        demo02.setMain(BigDecimal.valueOf(1));
        Demo03 demo03 = new Demo03();

        BeanUtils.copyProperties(demo02,demo03);
        System.out.println(demo03);

    }


}
