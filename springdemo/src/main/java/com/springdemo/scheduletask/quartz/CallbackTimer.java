package com.springdemo.scheduletask.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@DisallowConcurrentExecution
public class CallbackTimer extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(CallbackTimer.class);
    @Autowired
    private StrategyContext strategyContext;

    public CallbackTimer() {
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("CallbackTimer invoked. Time is: {}", new Date());
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        JobModel jobModel = JobDetailHelper.buildJobModel(jobDataMap);
        String name = jobDetail.getKey().getName();
        String group = jobDetail.getKey().getGroup();
        if (null != jobModel) {
            this.strategyContext.callback(jobModel);
            logger.info("Timer task with name:{}, gourp:{} invoke successfully.", name, group);
        } else {
            logger.info("Timer task with name:{}, gourp:{} invoke failed. JobDataMap is null or empty.", name, group);
        }

    }
}
