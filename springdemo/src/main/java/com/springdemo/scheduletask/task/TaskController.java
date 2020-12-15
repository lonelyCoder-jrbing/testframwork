package com.springdemo.scheduletask.task;

import com.springdemo.scheduletask.quartz.CallbackType;
import com.springdemo.scheduletask.quartz.JobModel;
import com.springdemo.scheduletask.quartz.SchedulerExecuteType;
import com.springdemo.scheduletask.quartz.SchedulerService;
import com.springdemo.scheduletask.spring.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TaskController {

    @Autowired
    private MonitorFacade facade;

//    @GetMapping(value = "/demo/request")
////    @Scheduled(cron = "0 0 1 * * ?")    //每天凌晨1点执行
//    @Scheduled(fixedRate = 5000)
//    public void leadOthers() {
//        facade.makeMoney();
//        facade.wakeup();
//        facade.sleep();
//
//    }

    @Autowired
    private SchedulerService schedulerService;

    @GetMapping(value = "/demo/request1")
    public void request1() {

        LocalDate data = LocalDate.now();

        log.info("afterPropertiesSet###=>{}", data);

        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        String format = dtf2.format(data);
        Integer integer = Integer.valueOf(format.split(":")[1]);
        Integer i1 = integer + 1;
        String str;
        if (i1 < 10) {
            str = "0" + i1;
        }
        str = ""+i1;
        String concat = format.split(":")[0].concat(":").concat(str);
        String strDate3 = dtf3.format(data);
        System.out.println("=====strDate4======" + strDate3);

        strDate3 = strDate3 + " "+concat;
        System.out.println("strDate3======>" + strDate3);
        LocalDateTime time1 = LocalDateTime.parse(strDate3, dtf2);


        JobModel jobModel = new JobModel();
        jobModel.setBeanName("OffLineTestController");
        jobModel.setMethodName("createSeqNo");
        jobModel.setJsonParam("");
        jobModel.setName("offline-app-load-data-job2");
        jobModel.setCron("0 * * * * ?");
        jobModel.setConnectTimeout(300);
        jobModel.setExecuteType(SchedulerExecuteType.CYCLE);
        jobModel.setCallbackType(CallbackType.JAVA_CODE);
        jobModel.setGroup("offline");
//        jobModel.setCallbackUrl("http://localhost:8080/api/api/offline/createSeqNo");
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime endTime = date.with(TemporalAdjusters.firstDayOfNextYear());
        jobModel.setFireTime(time1);
        jobModel.setEndTime(endTime);

        schedulerService.createJob("123",jobModel);

    }


    @GetMapping(value = "/demo/request2")
    public void request2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Object monitorFacade = SpringContextUtils.getBean("MonitorFacade");
        Method wakeup = monitorFacade.getClass().getDeclaredMethod("wakeup");
        wakeup.setAccessible(true);
        Object invoke = wakeup.invoke(monitorFacade);

    }


}
