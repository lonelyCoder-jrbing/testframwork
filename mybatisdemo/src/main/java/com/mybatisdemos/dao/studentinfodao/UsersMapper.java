package com.mybatisdemos.dao.studentinfodao;

import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.vo.ResultVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * create by sumerian on 2020/5/1
 * <p>
 * desc:
 **/
@Mapper
public interface UsersMapper {

   List<Map<String, String>>selectList(@Param("list") List<Integer> list);


}
