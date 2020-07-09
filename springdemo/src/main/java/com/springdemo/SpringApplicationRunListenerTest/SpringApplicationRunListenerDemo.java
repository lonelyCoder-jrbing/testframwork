package com.springdemo.SpringApplicationRunListenerTest;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * create by sumerian on 2020/7/9
 * <p>
 * desc:
 **/
public class SpringApplicationRunListenerDemo implements SpringApplicationRunListener {


    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {

    }
}
