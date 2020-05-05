package com.springdemo.initaillizebeanmethod.controller;

import com.springdemo.initaillizebeanmethod.InitMethodTest;
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
@RequestMapping("/InitMethod")
public class InitMethodController {
    @Autowired
    private InitMethodTest.Car car;

    @GetMapping("/getCarInsstance")
    public InitMethodTest.Car getCarInsstance() {
        return car;

    }


}
