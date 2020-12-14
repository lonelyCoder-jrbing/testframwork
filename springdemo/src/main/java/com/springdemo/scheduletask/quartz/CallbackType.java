package com.springdemo.scheduletask.quartz;

public enum CallbackType {
    URL(0L, "URL"),
    QUEUE(1L, "QUEUE"),
    JAVA_CODE(2L, "JAVA_CODE");

    private final long code;
    private final String name;

    private CallbackType(long code, String name) {
        this.code = code;
        this.name = name;
    }

    public long getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}