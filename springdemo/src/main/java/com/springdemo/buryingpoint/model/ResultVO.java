package com.springdemo.buryingpoint.model;

import lombok.Data;

@Data
public class ResultVO {
    private String code;

    private String date;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public String getDate() {
        return this.date;
    }

    public String getMsg() {
        return this.msg;
    }
}
