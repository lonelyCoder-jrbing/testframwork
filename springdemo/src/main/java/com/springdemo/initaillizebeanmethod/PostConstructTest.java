package com.springdemo.initaillizebeanmethod;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * create by sumerian on 2020/5/4
 * <p>
 * desc:
 **/
@Component
@Data
public class PostConstructTest {

    private String name;

    @PostConstruct
    private void _init() {
        name = "boyingyue";

    }


}
