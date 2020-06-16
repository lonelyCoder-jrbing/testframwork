package com.springdemo.beanregistrationdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person01 {
    private String name;
    private int age;
}
