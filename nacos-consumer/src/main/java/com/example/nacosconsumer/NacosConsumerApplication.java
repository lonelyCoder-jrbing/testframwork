package com.example.nacosconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.example.nacosconsumer.constant.NacosConfigValues;
import com.test.demoprovider.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
@EnableDubbo
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@NacosPropertySource(dataId = "nacos-consumer", autoRefreshed = true)
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }
    @Autowired
    private NacosConfigValues nacosConfigValues;


    @Autowired
    private RestTemplate restTemplate;
//    @Reference
    DemoService demoService;

    @NacosValue(value = "${service.name}", autoRefreshed = true)
    private String serverName;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/consumer")
    public String test1() {
        return restTemplate.getForObject("http://nacos-provider/helloNacos", String.class);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public String getCounsumerTest() {
        demoService.test("22");
        return null;
    }
    @RequestMapping(value = "getValueFromNacos", method = RequestMethod.GET)
    @ResponseBody
    public String getValueFromNacos() {
        System.out.println("servicename:"+nacosConfigValues.getName());
        System.out.println("servicename:"+nacosConfigValues.getNameSpace());
        return nacosConfigValues.getName();
    }



}
