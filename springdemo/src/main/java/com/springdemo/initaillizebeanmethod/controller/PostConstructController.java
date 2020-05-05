package com.springdemo.initaillizebeanmethod.controller;

import com.springdemo.initaillizebeanmethod.PostConstructTest;
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
@RequestMapping("PostConstruct")
public class PostConstructController {

    @Autowired
    private PostConstructTest postConstructTest;

    @GetMapping("/name")
    private String getName() {
        return postConstructTest.getName();
    }


}
