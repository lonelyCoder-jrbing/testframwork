package com.mybatisdemos.serivce.dto;

import lombok.Data;

import java.util.Date;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc:
 **/
@Data
public class ConfirmDTO {

    private String orderId;
    private String orderInfo;
    private String sendStatus;
    private Date createTime;
}
