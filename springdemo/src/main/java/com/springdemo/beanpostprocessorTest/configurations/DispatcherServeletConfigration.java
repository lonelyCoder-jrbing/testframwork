package com.springdemo.beanpostprocessorTest.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * create by sumerian on 2020/5/15
 * <p>
 * desc:或者将这个添加到application.properties在src\main\resources文件夹
 * server.contextPath=/com/url/jurongbing/*
 **/
@Configuration
@Slf4j
public class DispatcherServeletConfigration {

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.getUrlMappings().clear();
        log.info("mapping.......");
        registrationBean.addUrlMappings("/com/url/jurongbing/*");
        registrationBean.addUrlMappings("/com/url/jurongbing2/*");
        return registrationBean;

    }
}
