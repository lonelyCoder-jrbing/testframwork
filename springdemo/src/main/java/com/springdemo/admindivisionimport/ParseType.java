package com.springdemo.admindivisionimport;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ParseType {

    ADMIN_PCC("0001", "行政区划"), ADMIN_C("0002", "国家和地区名称代码");
    private String code;
    private String name;

    ParseType(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
