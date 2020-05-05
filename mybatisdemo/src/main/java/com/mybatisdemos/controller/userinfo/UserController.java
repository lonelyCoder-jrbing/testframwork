package com.mybatisdemos.controller.userinfo;

import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.intercepters.page.PageView;
import com.mybatisdemos.serivce.UserService;
import com.mybatisdemos.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    //mapper中传入list的demo
    //mapper中查询结果如果为空会返回null ，所以在业务代码中需要做判空处理
    @GetMapping(value = "/sel")
    public List<Map<String, String>> del(@RequestParam  Integer id){
      List<Integer> list =  new ArrayList<>();
        list.add(id);
        list.add(2);
      return   userService.selectByList(list);

    }


}
