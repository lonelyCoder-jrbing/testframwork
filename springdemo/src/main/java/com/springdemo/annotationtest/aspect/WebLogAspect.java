package com.springdemo.annotationtest.aspect;

import com.alibaba.fastjson.JSON;
import com.springdemo.annotationtest.annotations.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 *
 * 使用aspectj的方式对 加注解的方法进行拦截
 *
 */
@Aspect // 申明这是一个切面
@Component // 使其被Spring扫描管理,是采用了aop的方式，其最终还是由spring统一管理bean的。
@Slf4j
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /** 切入点 **/
    @Pointcut("@annotation(com.springdemo.annotationtest.annotations.WebLog)")
    public void webLogPointcut(){}
    // 在执行方法前后调用Advice，相当于@Before和@AfterReturning一起做的事儿；
    @Around("webLogPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        Long startTime = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取注解的属性值
        WebLog webLog = ((MethodSignature)pjp.getSignature()).getMethod().getAnnotation(WebLog.class);
        logger.info("请求方法描述：" + webLog.description() );
        logger.info("请求开始时间："+ LocalDateTime.now());
        // 记录下请求内容
        logger.info("请求Url : " + request.getRequestURL().toString());
        logger.info("请求方式 : " + request.getMethod());
        logger.info("请求ip : " + request.getRemoteAddr());
        logger.info("请求方法 : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(pjp.getArgs()));
        Object obj = pjp.proceed();
        logger.info("请求结束时间："+ LocalDateTime.now());
        logger.info("请求返回 : " + JSON.toJSONString(obj));
        logger.info("日志耗时：{} ms",(System.currentTimeMillis() - startTime));
        return obj;
    }
    @AfterReturning(value = "execution(* com.springdemo.annotationtest.controllers.WebLogTestController.webLogTest(..)) && args(strs)")
    public void saveDeptOrUser(String  strs) throws Throwable {
       log.info("strs:==={}",strs);
    }
}
