package com.springdemo.ehcachedemo.controller;

import com.springdemo.ehcachedemo.dto.GetUserRequest;
import com.springdemo.ehcachedemo.entity.Users;
import com.springdemo.ehcachedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * create by sumerian on 2020/8/23
 * <p>
 * desc:
 **/
@RestController
@RequestMapping(path = "ehcache")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(path = "get")
    public Users getUser(@RequestParam Integer id) {
        return userService.selectUserById(id);
    }


    @PostMapping(path = "getMany")
    public List<Users> getMany(@RequestBody GetUserRequest request) {

        return userService.selectUserByIds(request.getIds());
    }





}
