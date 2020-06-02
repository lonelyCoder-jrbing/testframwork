package com.mybatisdemos.dao.studentinfodao;

//import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.dao.sqlPprovider.UserDaoProvider;
import com.mybatisdemos.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * create by sumerian on 2020/5/1
 * <p>
 * desc:
 **/
@Mapper
public interface UsersMapper {

    List<Map<String, String>> selectList(@Param("list") List<Integer> list);

    User selectUser(@Param("id") String id);

    @SelectProvider(type = UserDaoProvider.class, method = "findUserById")
    User selectUserById(User user);


    int insertBatch(@Param("userList") List<User> userList);

    int updateBatch(@Param("userList") List<User> userList);
}
