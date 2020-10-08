package com.springdemo.ehcachedemo.service.impl;

import com.springdemo.ehcachedemo.entity.Users;
import com.springdemo.ehcachedemo.service.UserService;
import com.springdemo.ehcachedemo.userdao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional     //事务，表示该类下所有的都受事务控制
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao usermapper;

    @Override
    @Cacheable(value = "users")
    public Users selectUserById(int id) {
        Users users = new Users();
        users.setUserId(id);
        Example<Users> of = Example.of(users);
        Optional<Users> one = this.usermapper.findOne(of);
        System.out.println("1111111111111111111111111" + one.get());
        return one.get();
    }

    @Override
    @Cacheable(value = "users")
    public List<Users> selectUserByIds(List<Integer> ids) {
        log.info("ids:   {}",ids);
        return this.usermapper.findAllById(ids);
    }

}