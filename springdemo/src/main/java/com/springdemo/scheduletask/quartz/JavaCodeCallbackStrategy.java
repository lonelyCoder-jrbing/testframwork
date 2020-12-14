package com.springdemo.scheduletask.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.springdemo.scheduletask.spring.SpringContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.rpc.ServiceException;
import java.lang.reflect.Method;

@Component("JAVA_CODE")
public class JavaCodeCallbackStrategy implements CallbackStrategy {
    private Logger logger = LoggerFactory.getLogger(JavaCodeCallbackStrategy.class);

    public JavaCodeCallbackStrategy() {
    }

    @Override
    public boolean callback(JobModel jobModel) {
        this.logger.debug("Start to invoke java code Callback...");
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("jobModel = [{}]", JSON.toJSONStringWithDateFormat(jobModel, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]));
        }

        String beanName = jobModel.getBeanName();
        String methodName = jobModel.getMethodName();
        if (!StringUtils.isBlank(beanName) && !StringUtils.isBlank(methodName)) {
            String jsonParam = jobModel.getJsonParam();
            Object targetBean = SpringContextUtils.getBean(beanName);
            this.logger.info("beanName = {}, methodName = {}, jsonParam = {}.", new Object[]{beanName, methodName, jsonParam});

            try {
                Method method;
                Object object;
                if (StringUtils.isBlank(jsonParam)) {
                    method = targetBean.getClass().getDeclaredMethod(methodName);
                    method.setAccessible(true);
                    object = method.invoke(targetBean);
                } else {
                    method = targetBean.getClass().getDeclaredMethod(methodName, String.class);
                    method.setAccessible(true);
                    object = method.invoke(targetBean, jsonParam);
                }

                this.logger.info("Callback for java code successful. returnValue = {}.", JSON.toJSONString(object));
                return true;
            } catch (Exception var9) {
                this.logger.error("Callback for java code failed due to error.", var9);
                return false;
            }
        } else {
            this.logger.warn("beanName and methodName must bo not null.");
        }
        return true;
    }
}