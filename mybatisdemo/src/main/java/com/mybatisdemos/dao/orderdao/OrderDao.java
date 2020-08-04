package com.mybatisdemos.dao.orderdao;

import com.mybatisdemos.controller.distributetransactionbymq.request.OrderInfoRequest;
import com.mybatisdemos.serivce.dto.ConfirmDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by sumerian on 2020/8/3
 * <p>
 * desc:
 **/
@Mapper
public interface OrderDao {

   boolean insertOrder(@Param("request") OrderInfoRequest request);


    boolean insertConfirm(@Param("request") ConfirmDTO request);

    boolean updateConfirm(@Param("confirmDTO")ConfirmDTO confirmDTO);

    List<OrderInfoRequest> queryForList(@Param("confirmDTO") ConfirmDTO confirmDTO);

}
