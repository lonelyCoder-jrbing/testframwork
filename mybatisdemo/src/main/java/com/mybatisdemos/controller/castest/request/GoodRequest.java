package com.mybatisdemos.controller.castest.request;

import lombok.Data;

import java.io.Serializable;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc: 商品
 **/
@Data
public class GoodRequest implements Serializable {
    private static final long serialVersionUID = -8239732682333468238L;
    //主键
    private Long id;
    //商品状态
    //status:商品状态：1未下单、2已下单.
    private String status;
    //商品名称
    private String name;
    //商品库存
    private Integer buyNum;
}
