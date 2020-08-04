package com.mybatisdemos.controller.distributetransactionbymq.request;

import lombok.Data;

import java.util.Date;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc:
 **/
@Data
public class OrderInfoRequest {

    private String orderId;
    private String userId;
    private String orderContent;
    private Date createTime;

}
