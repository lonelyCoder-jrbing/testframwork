package com.springdemo.initaillizebeanmethod;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * create by sumerian on 2020/5/4
 * <p>
 * desc:
 **/
@Component
public class InitializingBeanTest implements InitializingBean {
    private String name;

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

    }
}
