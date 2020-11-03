package com.springdemo.admindivisionimport;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class DicVO {

    /**
     * 姓名
     */
    @ExcelProperty(value = "行政区划代码")
    @Length(min = 1, max = 3)
    private String code;
    /**
     *
     */
    @ExcelProperty(value = "单位名称")
    @Length(min = 1, max = 3)
    private String name;


}
