package com.mybatisdemos.dao.userinfodao;

import com.mybatisdemos.domain.UserLoginPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserLoginPOMapper {
    int insert(UserLoginPO record);

    int insertSelective(UserLoginPO record);

    int insertUserBatch(@Param("userLoginPOS") List<UserLoginPO> userLoginPOS);


}