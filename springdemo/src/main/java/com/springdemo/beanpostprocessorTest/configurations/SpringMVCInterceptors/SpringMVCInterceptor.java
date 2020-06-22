package com.springdemo.beanpostprocessorTest.configurations.SpringMVCInterceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;


//@Configuration
@Slf4j
public class SpringMVCInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("在请求处理之前执行该方法-preHandle");
        request.setAttribute("startTime", System.currentTimeMillis());
        String requestURI = request.getRequestURI();
         log.info("requestURI:   {}",requestURI);
        String method = request.getMethod();
        log.info("method:==={}",method);
        HttpSession session = request.getSession();
        log.info("handler==={}",handler);
        String traceId = UUID.randomUUID().toString();
        log.info("traceId:   {}",traceId);
        response.setHeader("traceId",traceId);
        log.info("session=={}",session);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
