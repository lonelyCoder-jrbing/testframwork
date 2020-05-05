package com.mybatisdemos.dao.userinfodao;

import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.intercepters.page.PageView;
import com.mybatisdemos.vo.ResultVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Component
public interface IUserMapper {
    //使用注解的方式进行查询
    @Select("SELECT * FROM user")
    public List<IUser> getAllUserPage();

    List<ResultVo> selectList(List<String> list);
}
