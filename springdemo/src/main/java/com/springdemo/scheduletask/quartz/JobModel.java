package com.springdemo.scheduletask.quartz;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class JobModel implements Serializable {
    private static final long serialVersionUID = -3513715866387884494L;
    private String name;
    private String group;
    private LocalDateTime fireTime;
    private SchedulerExecuteType executeType;
    private String cron;
    private Long interval;
    private LocalDateTime endTime;
    private CallbackType callbackType;
    private String callbackUrl;
    private Integer connectTimeout;
    private Integer readTimeout;
    private String topic;
    private String messageContent;
    private String beanName;
    private String methodName;
    private String jsonParam;
    private String state;

    public JobModel() {
        this.executeType = SchedulerExecuteType.CYCLE;
        this.interval = 0L;
        this.callbackType = CallbackType.URL;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public LocalDateTime getFireTime() {
        return this.fireTime;
    }

    public void setFireTime(LocalDateTime fireTime) {
        this.fireTime = fireTime;
    }

    public String getCron() {
        return this.cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getCallbackUrl() {
        return this.callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Integer getConnectTimeout() {
        if (this.connectTimeout == null) {
            return 0;
        } else {
            if (this.connectTimeout <= 0) {
                this.connectTimeout = 5000;
            }

            return this.connectTimeout;
        }
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        if (this.readTimeout == null) {
            return 0;
        } else {
            if (this.readTimeout <= 0) {
                this.readTimeout = 10000;
            }

            return this.readTimeout;
        }
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public CallbackType getCallbackType() {
        return this.callbackType;
    }

    public void setCallbackType(CallbackType callbackType) {
        this.callbackType = callbackType;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessageContent() {
        return this.messageContent;
    }

    public void setMessageContent(String content) {
        this.messageContent = content;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getJsonParam() {
        return this.jsonParam;
    }

    public void setJsonParam(String jsonParam) {
        this.jsonParam = jsonParam;
    }

    public SchedulerExecuteType getExecuteType() {
        return this.executeType;
    }

    public void setExecuteType(SchedulerExecuteType executeType) {
        this.executeType = executeType;
    }

    public Long getInterval() {
        return this.interval == null ? 0L : this.interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}