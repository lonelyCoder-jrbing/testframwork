package com.springdemo.scheduletask.quartz;

import org.quartz.JobDataMap;

import java.time.LocalDateTime;

public class JobDetailHelper {
    public JobDetailHelper() {
    }

    public static JobModel buildJobModel(JobDataMap jobDataMap) {
        if (null == jobDataMap) {
            return null;
        } else {
            JobModel jobModel = new JobModel();
            String group = jobDataMap.getString("group");
            String name = jobDataMap.getString("name");
            Object fireTime = jobDataMap.get("fireTime");
            Object endTime = jobDataMap.get("endTime");
            Object executeType = jobDataMap.get("executeType");
            String cron = jobDataMap.getString("cron");
            Long interval = jobDataMap.getLongValue("interval");
            Object callbackType = jobDataMap.get("callbackType");
            String callbackUrl = jobDataMap.getString("callbackUrl");
            Integer connectTimeout = jobDataMap.getIntValue("connectTimeout");
            Integer readTimeout = jobDataMap.getIntValue("readTimeout");
            String topic = jobDataMap.getString("topic");
            String messageContent = jobDataMap.getString("messageContent");
            String beanName = jobDataMap.getString("beanName");
            String methodName = jobDataMap.getString("methodName");
            String jsonParam = jobDataMap.getString("jsonParam");
            jobModel.setGroup(group);
            jobModel.setName(name);
            jobModel.setFireTime((LocalDateTime)fireTime);
            jobModel.setEndTime((LocalDateTime)endTime);
            jobModel.setExecuteType((SchedulerExecuteType)executeType);
            jobModel.setCron(cron);
            jobModel.setInterval(interval);
            jobModel.setCallbackType((CallbackType)callbackType);
            jobModel.setCallbackUrl(callbackUrl);
            jobModel.setConnectTimeout(connectTimeout);
            jobModel.setReadTimeout(readTimeout);
            jobModel.setTopic(topic);
            jobModel.setMessageContent(messageContent);
            jobModel.setBeanName(beanName);
            jobModel.setMethodName(methodName);
            jobModel.setJsonParam(jsonParam);
            return jobModel;
        }
    }

    public static void buildJobDataMap(JobDataMap jobDataMap, JobModel jobModel) {
        jobDataMap.put("name", jobModel.getName());
        jobDataMap.put("group", jobModel.getGroup());
        jobDataMap.put("fireTime", jobModel.getFireTime());
        jobDataMap.put("endTime", jobModel.getEndTime());
        jobDataMap.put("executeType", jobModel.getExecuteType());
        jobDataMap.put("cron", jobModel.getCron());
        jobDataMap.put("interval", jobModel.getInterval());
        jobDataMap.put("callbackType", jobModel.getCallbackType());
        jobDataMap.put("topic", jobModel.getTopic());
        jobDataMap.put("messageContent", jobModel.getMessageContent());
        jobDataMap.put("callbackUrl", jobModel.getCallbackUrl());
        jobDataMap.put("connectTimeout", jobModel.getConnectTimeout());
        jobDataMap.put("readTimeout", jobModel.getReadTimeout());
        jobDataMap.put("beanName", jobModel.getBeanName());
        jobDataMap.put("methodName", jobModel.getMethodName());
        jobDataMap.put("jsonParam", jobModel.getJsonParam());
    }
}