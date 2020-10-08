package com.springdemo.ehcachedemo.userdao;

import com.springdemo.ehcachedemo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create by sumerian on 2020/8/23
 * <p>
 * desc:
 **/
public interface UserDao extends JpaRepository<Users, Integer>{

}
