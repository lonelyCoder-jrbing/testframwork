package com.mybatisdemos.dao.userinfodao;

import com.mybatisdemos.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
//注解方式开发持久层接口
/**
 * @author: LzCc
 * @blog: https://blog.csdn.net/qq_41744145
 * @description: 基于注解开发的持久层接口
 */
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 根据 id 查询一个用户
     *
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{uid}")
    default User findById(Integer userId) {
        return null;
    }

    /**
     * 保存操作
     *
     * @param user
     * @return
     */
    @Insert("insert into user(username, sex, birthday, address)values(#{username}, #{sex}, #{birthday}, #{address})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Integer.class, before = false, statement = {"select last_insert_id()"})
    int saveUser(User user);
    /**
     * 更新操作
     *
     * @param user
     * @return
     */
    @Update("update user set username=#{username}, address=#{address}, sex=#{sex}, birthday=#{birthday} where id = #{id}")
    int updateUser(User user);
    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Delete("delete from user where id = #{uid}")
    int deleteUser(Integer userId);
    /**
     * 查询使用聚合函数
     *
     * @return
     */
    @Select("select count(*) from user")
    int findTotal();
    /**
     * &#x6a21;&#x7cca;&#x67e5;&#x8be2;
     *
     * @param name
     * @return
     */
    @Select("select * from user where username like #{username}")
    List<User> findByName(String name);
}
