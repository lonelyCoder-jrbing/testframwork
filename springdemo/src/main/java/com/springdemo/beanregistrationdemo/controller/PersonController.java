package com.springdemo.beanregistrationdemo.controller;

import com.springdemo.beanregistrationdemo.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
@RestController
@RequestMapping("/getperson")
@Slf4j
public class PersonController {

    @Autowired
    private Person01 person01;

    @Autowired
    private Person02 person02;

    @Autowired
    private Person03 person03;

    @Autowired
    private Person04 person04;

    @Autowired
    private Person05 person05;
    @RequestMapping("/test01")
    public void getPerson() {
        log.info("person01===={}", person01);
        log.info("person02===={}", person02);
        log.info("person03===={}", person03);
        log.info("person04===={}", person04);
        log.info("person05===={}", person05);

    }

}
