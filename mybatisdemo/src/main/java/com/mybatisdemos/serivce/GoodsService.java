package com.mybatisdemos.serivce;

import com.mybatisdemos.controller.distributetransactionbymq.request.OrderInfoRequest;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc: 用户下单service
 **/
public interface GoodsService {

    void createOrder(OrderInfoRequest request);


}
