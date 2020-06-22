package com.springdemo.beanpostprocessorTest.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by sumerian on 2020/5/15
 * <p>
 * desc:
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object handlerException(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        //可以对url进行检查
        log.info("request.getRequestURI==={}",request.getRequestURI());
        if (request.getRequestURI().contains("/com")) {
            log.info("com.......");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return null;
    }

}
