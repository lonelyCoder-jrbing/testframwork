package com.springdemo.scheduletask.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TaskController {

    @Autowired
    private MonitorFacade facade;

    @GetMapping(value = "/demo/request")
//  @Scheduled(cron = "0 0 1 * * ?")    //每天凌晨1点执行
    @Scheduled(fixedRate = 5000)
    public void leadOthers() {
        facade.makeMoney();
        facade.wakeup();
        facade.sleep();
    }


}
