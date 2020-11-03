package com.springdemo.initaillizebeanmethod.controller;

import com.springdemo.initaillizebeanmethod.InitializingBeanTest;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by sumerian on 2020/5/4
 * <p>
 * desc:
 **/
@RestController
@RequestMapping("/load")
@Slf4j
public class LoadBeanController {
    @Data
    @ToString
    static class A {
        private String name;

    }


    @Autowired
    private InitializingBeanTest initializingBeanTest;

    @PostMapping("/test01")
    private void getname(@RequestBody A a,String age) {
        log.info("a=>{}  age=>{}",a,age);

        String name = initializingBeanTest.getName();
        System.out.println(name);
    }

}
