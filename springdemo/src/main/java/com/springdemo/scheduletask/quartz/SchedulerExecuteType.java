package com.springdemo.scheduletask.quartz;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SchedulerExecuteType {
    POLLING("轮询执行", 0),
    CYCLE("按周期执行", 1);

    private final String name;
    private final int index;

    private SchedulerExecuteType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @JsonCreator
    public static SchedulerExecuteType get(int index) {
        SchedulerExecuteType[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            SchedulerExecuteType status = var1[var3];
            if (status.getIndex() == index) {
                return status;
            }
        }

        return null;
    }

    public String getName() {
        return this.name;
    }

    @JsonValue
    public int getIndex() {
        return this.index;
    }
}
