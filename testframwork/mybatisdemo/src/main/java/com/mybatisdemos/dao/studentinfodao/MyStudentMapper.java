package com.mybatisdemos.dao.studentinfodao;

import com.mybatisdemos.domain.MyStudent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyStudentMapper {
    int insert(MyStudent record);

    int insertSelective(MyStudent record);
}