package com.springdemo.ehcachedemo.service;

import com.springdemo.ehcachedemo.entity.Users;

import java.util.List;

/**
 * create by sumerian on 2020/8/23
 * <p>
 * desc:
 **/
public interface UserService {
    public Users selectUserById(int id);

    List<Users> selectUserByIds(List<Integer> ids);

}
