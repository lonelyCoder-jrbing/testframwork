package com.mybatisdemos.dao.sqlPprovider;

import com.mybatisdemos.domain.User;

/**
 * create by sumerian on 2020/5/24
 * <p>
 * desc:
 **/
public class UserDaoProvider {
    public String findUserById(User user) {
        String sql = "SELECT * FROM User";
        if(user.getId()!=null){
            sql += " where id = #{id}";
        }
        System.out.println("sql:  "+sql);
        return sql;
    }
}
