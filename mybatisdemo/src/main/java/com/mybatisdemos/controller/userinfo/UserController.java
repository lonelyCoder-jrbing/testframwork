package com.mybatisdemos.controller.userinfo;

import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.intercepters.page.PageView;
import com.mybatisdemos.serivce.UserService;
import com.mybatisdemos.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/try")
@EnableAutoConfiguration
public class UserController {

 
    @Autowired
    private UserService userService;
 
    @RequestMapping(value = "/user")
    public ResultVo getUsers(){
        ResultVo allUsers = userService.getAllUsers();
        return allUsers;
    }
}
