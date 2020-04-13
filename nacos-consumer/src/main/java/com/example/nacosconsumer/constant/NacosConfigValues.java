package com.example.nacosconsumer.constant;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * create by sumerian on 2020/4/11
 * <p>
 * desc:
 **/


public class NacosConfigValues {
    @NacosValue("${service.name}")
    private String name;

    private String nameSpace;

    private String dataId;


    @PostConstruct
    public void init() {
        this.nameSpace = "jjjadkladsasasda";
        this.dataId = "nacos-consumer";
    }
}
