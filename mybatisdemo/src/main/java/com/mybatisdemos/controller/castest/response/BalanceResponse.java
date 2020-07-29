package com.mybatisdemos.controller.castest.response;

import lombok.Data;

import java.io.Serializable;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
@Data
public class BalanceResponse implements Serializable {

    private static final long serialVersionUID = -4393240120293040895L;
    private Integer id;
    private String name;
    private Float balance;
    private Integer version;

}