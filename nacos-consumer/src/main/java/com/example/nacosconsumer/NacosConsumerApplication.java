package com.example.nacosconsumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.test.demoprovider.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;
    @Reference
    DemoService demoService;

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


}
