package com.example.nacosconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }

//    @Autowired
//    private NacosConfigValues nacosConfigValues;
//
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Reference(version = "${echo.service.version}",check = false)
//    DemoService demoService;
//
//    @NacosValue(value = "${service.name}", autoRefreshed = true)
//    private String serverName;
//
//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }
//
//    @GetMapping("/consumer")
//    public String test1() {
//        return restTemplate.getForObject("http://nacos-provider/helloNacos", String.class);
//    }
//
//    @RequestMapping(value = "test", method = RequestMethod.GET)
//    @ResponseBody
//    public String getCounsumerTest() {
//        System.out.println("dubbo version::::" + "${echo.service.version}");
//        demoService.test("22");
//        return "";
//    }
//
//    @RequestMapping(value = "getValueFromNacos", method = RequestMethod.GET)
//    @ResponseBody
//    public String getValueFromNacos() {
//        System.out.println("servicename:" + nacosConfigValues.getName());
//        System.out.println("servicename:" + nacosConfigValues.getNameSpace());
//        return nacosConfigValues.getName();
//    }


}
