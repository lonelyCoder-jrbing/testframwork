package com.springdemo.initaillizebeanmethod.controller;

import com.springdemo.initaillizebeanmethod.InitializingBeanTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by sumerian on 2020/5/4
 * <p>
 * desc:
 **/
@RestController
@RequestMapping("/load")
public class LoadBeanController {
    @Autowired
    private InitializingBeanTest initializingBeanTest;

    @GetMapping("/test01")
    private void getname() {
        String name = initializingBeanTest.getName();
        System.out.println(name);
    }

}
