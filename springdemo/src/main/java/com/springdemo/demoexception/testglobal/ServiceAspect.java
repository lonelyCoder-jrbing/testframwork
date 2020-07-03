package com.springdemo.demoexception.testglobal;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * create by sumerian on 2020/6/18
 * <p>
 * desc:拦截待遇service注解的类中的方法
 **/

@Aspect
@Component
public class ServiceAspect {
    private final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
    // 切入点表达式按需配置
    @Pointcut("@within(org.springframework.stereotype.Service) && execution(public * *(..))")
    private void myPointcut() {
    }
 
    @Before("@within(org.springframework.stereotype.Service) && execution(public * *(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.warn(className + "的" + methodName + "执行了");
        Object[] args = joinPoint.getArgs();
        StringBuilder log = new StringBuilder("入参为");
        for (Object arg : args) {
            log.append(arg + " ");
        }
        logger.warn(log.toString());
    }
 
    @AfterReturning(value = "@within(org.springframework.stereotype.Service) && execution(public * *(..))", returning = "returnVal")
    public void afterReturin(Object returnVal) {
        logger.warn("方法正常结束了,方法的返回值:" + returnVal);
    }
 
    @AfterThrowing(value = "com.springdemo.demoexception.testglobal.ServiceAspect.myPointcut()", throwing = "e")
    public void afterThrowing(Throwable e) {
        if (e instanceof BizException) {
            logger.error("通知中发现异常StationErrorCodeException", e);
        } else {
            logger.error("通知中发现未知异常", e);
        }
    }
 
    @Around(value = "com.springdemo.demoexception.testglobal.ServiceAspect.myPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.warn("前置增强...");
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            PaasResponnse resultDTO = new PaasResponnse();
            if (e instanceof BizException) {
                BizException errorCodeException = (BizException) e;
                resultDTO.setCode(errorCodeException.getErrorCode());
                resultDTO.setMsg(errorCodeException.getErrorMsg());
            } else {
                resultDTO.setCode(ExceptionCode.ERROR.getCode());
                resultDTO.setMsg(ExceptionCode.ERROR.getName());
            }
            return resultDTO;
        }
        return result;
    }
}