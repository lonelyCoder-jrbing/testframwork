package com.springdemo.initaillizebeanmethod;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * create by sumerian on 2020/5/4
 * <p>
 * desc:
 **/
@Component
@Data
@Slf4j
public class PostConstructTest {

    private String name;

    @PostConstruct
    private void _init() {
        log.info("_init......");
        name = "boyingyue";
    }

}
