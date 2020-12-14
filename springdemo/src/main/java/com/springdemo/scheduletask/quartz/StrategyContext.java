package com.springdemo.scheduletask.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StrategyContext {
    private final Map<String, CallbackStrategy> strategyMap = new ConcurrentHashMap();

    @Autowired
    public StrategyContext(Map<String, CallbackStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> {
            CallbackStrategy var10000 = (CallbackStrategy)this.strategyMap.put(k, v);
        });
    }

    public boolean callback(JobModel jobModel) {
        return ((CallbackStrategy)this.strategyMap.get(jobModel.getCallbackType().getName())).callback(jobModel);
    }
}