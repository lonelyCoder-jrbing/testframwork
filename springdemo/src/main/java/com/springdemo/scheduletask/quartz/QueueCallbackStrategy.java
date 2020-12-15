package com.springdemo.scheduletask.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("QUEUE")
public class QueueCallbackStrategy implements CallbackStrategy {
    private Logger logger = LoggerFactory.getLogger(QueueCallbackStrategy.class);

    public QueueCallbackStrategy() {
    }

    @Override
    public boolean callback(JobModel jobModel) {
        this.logger.debug("Start to invoke QueueCallback...");
        this.logger.error("========暂未实现该方法!");
        return false;
    }
}
