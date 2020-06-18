package com.mybatisdemos.controller.userinfo;

//import com.mybatisdemos.domain.IUser;

import com.mybatisdemos.domain.User;
import com.mybatisdemos.domain.UserLoginPO;
import com.mybatisdemos.rabbitmq.receiver.FirstConsumer;
import com.mybatisdemos.serivce.UserService;
import com.mybatisdemos.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/try")
@EnableAutoConfiguration
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user")
    public ResultVo getUsers() {
        ResultVo allUsers = userService.getAllUsers();
        return allUsers;
    }

    //mapper中传入list的demo
    //mapper中查询结果如果为空会返回null ，所以在业务代码中需要做判空处理
    @GetMapping(value = "/sel")
    public List<Map<String, String>> del(@RequestParam Integer id) {
        List<Integer> list = new ArrayList<>();
        list.add(id);
        list.add(2);
        return userService.selectByList(list);

    }

    @GetMapping(value = "/selectUser")
    public User selectUser(@RequestParam String id) {

        User user = userService.selectByid(id);

        return user;
    }

    @PostMapping(value = "/selectUserById")
    @ResponseBody
    public User selectUserById(@RequestBody User user) {

        User user01 = userService.selectUserById(user);

        return user01;
    }

    @PostMapping(value = "/insertUserBatch")
    @ResponseBody
    public int insertUserBatch() {

        List<UserLoginPO> userList = IntStream.range(0,50).mapToObj(i ->
                UserLoginPO.builder()
                        .userLoginId(UUID.randomUUID().toString())
                        .cipher(String.valueOf(new Random(4).nextInt(10000)))
                        .userCode("jrbing@001")
                        .userName("jurongbing" + i)
                        .userNickName("zhibo")
                        .userAge(20+i)
                        .loginTime(new Date())
                        .build()
        ).peek(el->log.info("user:  {}",el)).collect(Collectors.toList());

        int i = userService.insertUserbatch(userList);
        return i;
    }




    @PostMapping(value = "/insertBatch")
    @ResponseBody
    public int insertBatch() {

        List<User> userList = IntStream.range(0,50).mapToObj(i ->
                User.builder()
                        .id(i+11)
                        .password(String.valueOf(new Random(4).nextInt(10000)))
                        .patternLock("jrbing@001")
                        .userName("jurongbing" + i)
                        .build()
        ).peek(el->log.info("user:  {}",el)).collect(Collectors.toList());

        int i = userService.insertbatch(userList);
        return i;
    }
    @PostMapping(value = "/updateBatch")
    @ResponseBody
    public int updateBatch() {

        List<User> userList = IntStream.range(0,50).mapToObj(i ->
                User.builder()
                        .id(i+11)
                        .password(String.valueOf(new Random(4).nextInt(10000)))
                        .patternLock("byingyue")
                        .userName("jurongbing" + i)
                        .build()
        ).peek(el->log.info("user:  {}",el)).collect(Collectors.toList());

        int i = userService.updatebatch(userList);
        return i;
    }
}
