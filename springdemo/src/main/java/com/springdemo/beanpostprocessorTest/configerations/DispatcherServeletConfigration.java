package com.springdemo.beanpostprocessorTest.configerations;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * create by sumerian on 2020/5/15
 * <p>
 * desc:
 **/
@Configuration
public class DispatcherServeletConfigration {

    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.getUrlMappings().clear();
        registrationBean.addUrlMappings("/com/url/jurongbing");
        registrationBean.addUrlMappings("/com/url/jurongbing2");
        return registrationBean;

    }
}
