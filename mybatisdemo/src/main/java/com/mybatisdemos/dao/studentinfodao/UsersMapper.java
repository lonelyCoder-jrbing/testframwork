package com.mybatisdemos.dao.studentinfodao;

import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.vo.ResultVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by sumerian on 2020/5/1
 * <p>
 * desc:
 **/
@Mapper
public interface UserMapper {

   Mapper selectList(@Param("list") List<String> list);


}
