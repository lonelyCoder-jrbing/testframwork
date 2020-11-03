package com.springdemo.admindivisionimport;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity(name = "datadictionary")
@Table(name = AdministratorDivisionBO.TABLE_NAME)
public class AdministratorDivisionBO extends BaseBO {
    public static final String TABLE_NAME = "ipw7i_datadictionary";
    /****
     * 上级字典
     */
    @Column(name = "parent_id")
    private String parent_id;
    /*****
     *
     * 所属字典分类
     */
    @Column(name = "Datadictionary")
    private String DataDictionary;
    /******
     * 数据字典分类编码
     */
    @Column(name = "Datadictionarycode")
    private String DataDictionaryCode;
    /****
     *
     * 数据字典Key
     */
    @Column(name = "Datakey")
    private String DataKey;
    /*****
     * 数据字典Value
     */
    @Column(name = "Datavalue")
    private String DataValue;
    /*****
     * 排序数据
     */
    @Column(name = "Number1596185401203")
    private BigDecimal Number1596185401203;


}
