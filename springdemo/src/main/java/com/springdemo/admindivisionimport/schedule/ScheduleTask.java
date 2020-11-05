package com.springdemo.admindivisionimport.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@Slf4j
public class ScheduleTask implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
