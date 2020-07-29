package com.mybatisdemos.serivce;

import com.mybatisdemos.controller.castest.request.BalanceRequest;
import com.mybatisdemos.controller.castest.request.GoodRequest;
import com.mybatisdemos.controller.castest.response.BalanceResponse;
import com.mybatisdemos.controller.castest.response.GoodResponse;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
public interface BalanceService {

   boolean updateBalance(BalanceRequest request);

   boolean updateGoods(GoodRequest request);

   GoodResponse selectGoodsById(GoodRequest request);

   BalanceResponse getNewBlance(BalanceRequest request);

}
