package com.springdemo.buryingpoint.config;

import com.springdemo.buryingpoint.filter.HttpEventLogFilter;
import com.springdemo.buryingpoint.filter.HttpEventLogFilter1;
import com.springdemo.buryingpoint.filter.HttpEventLogFilter2;
import com.springdemo.buryingpoint.filter.ResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUtil {

    /**
     * 记录http请求日志
     * @return
     */
    @Bean
    public FilterRegistrationBean httpEventFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new HttpEventLogFilter2());
        registration.addUrlPatterns("/api/dosomething");
        return registration;
    }


}
