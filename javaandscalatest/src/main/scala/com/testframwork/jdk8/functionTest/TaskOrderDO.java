package com.testframwork.jdk8.functionTest;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TaskOrderDO {

    private Long id;

    private String produceOrderId;

    private String produceOrderCode;

    private String orderCode;

    private String batchNumber;

    private String batchName;

    private String productName;

    private String productCode;
    private String productUnit;
    private String spec;

    private String productLineName;

    private String producelineCode;

    private String pictureIcon;

    private Integer bomType;
    private String postingDate;

    private Date beginTime;


    private Date completeTime;

    private BigDecimal realProducedNum;

    private BigDecimal numsPlanProduce;

    private String orderStatus;

    private String transferStatus;

    private String produceTime;

    private String confirmTime;
    private String confirmPerson;
    private String remark;
    private String locationCode;

    private String wmsTransOrderCode;


}
