package com.springdemo.admindivisionimport;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseBO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "creater")
    private String creater;
    @Column(name = "createddeptid")
    private String createdDeptId;
    @Column(name = "owner")
    private String owner;
    @Column(name = "ownerdeptid")
    private String ownerDeptId;
    @Column(name = "createdtime")
    private Date createdTime;
    @Column(name = "modifier")
    private String modifier;
    @Column(name = "modifiedtime")
    private Date modifiedTime;
    @Column(name = "workflowinstanceid")
    private String workflowInstanceId;
    @Column(name = "sequenceno")
    private String sequenceNo;
    @Column(name = "sequencestatus")
    private String sequenceStatus;
    @Column(name = "ownerdeptquerycode")
    private String ownerDeptQueryCode;
    @Column(name = "business_id")
    private String business_id;

}
