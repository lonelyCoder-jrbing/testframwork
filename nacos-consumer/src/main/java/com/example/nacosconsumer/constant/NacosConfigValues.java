package com.example.nacosconsumer.constant;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * create by sumerian on 2020/4/11
 * <p>
 * desc:
 **/

@Component
@RefreshScope
@Data
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
