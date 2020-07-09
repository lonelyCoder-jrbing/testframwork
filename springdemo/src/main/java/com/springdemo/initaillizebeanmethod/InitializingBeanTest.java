package com.springdemo.initaillizebeanmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.RandomAccess;

/**
 * create by sumerian on 2020/5/4
 * <p>
 * desc:最为方便的将spring容器启动完成之前加载bean到容器中的方式
 *      在bean销毁的时候会调用destroy()方法。
 **/
@Component
@Slf4j
public class InitializingBeanTest implements InitializingBean, DisposableBean {
    private String name;

    private Random random;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //在spring容器启动完成之后，就加载到内存中了。
    @Override
    public void afterPropertiesSet() throws Exception {
        name = "jurongbing";
        random = new Random(12);
        log.info("random....init.....................");
    }

    @Override
    public void destroy() throws Exception {
        random = null;
        log.info("random....destroy.....................");
    }
}
