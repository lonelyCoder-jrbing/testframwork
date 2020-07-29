package com.mybatisdemos.dao.balancedao;

import com.mybatisdemos.controller.castest.request.BalanceRequest;
import com.mybatisdemos.controller.castest.request.GoodRequest;
import com.mybatisdemos.controller.castest.response.BalanceResponse;
import com.mybatisdemos.controller.castest.response.GoodResponse;
import com.mybatisdemos.controller.userstar.request.GenStarVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
@Mapper
public interface BalanceMapper {

    boolean updateBlance(@Param("request") BalanceRequest request);

    BalanceResponse getNewBlance(@Param("request") BalanceRequest request);

    boolean updateGoods(@Param("request")GoodRequest request);

    GoodResponse selectGoodsById(@Param("request")GoodRequest request);
}
