package com.mybatisdemos.serivce.impl;

import com.alibaba.fastjson.JSON;
import com.mybatisdemos.dao.studentinfodao.UsersMapper;
//import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.dao.userinfodao.IUserMapper;
import com.mybatisdemos.domain.User;
import com.mybatisdemos.sender.FirstSender;
import com.mybatisdemos.serivce.UserService;
import com.mybatisdemos.vo.CodeMsg;
import com.mybatisdemos.vo.ResultVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserMapper userDao;

    @Autowired
    private FirstSender firstSender;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public ResultVo getAllUsers() {
        List<User> allUsers = userDao.getAllUserPage();
        if (allUsers.size() == 0) {
            return ResultVo.error(CodeMsg.SELECT_ERROR);
        } else {
            return ResultVo.success(allUsers);
        }
    }

    @Override
    public List<Map<String, String>> selectByList(List<Integer> list) {
        return usersMapper.selectList(list);
    }

    @Override
    public User selectByid(String id) {
        User user = usersMapper.selectUser(id);
        String s = JSON.toJSONString(user);
        String uuid = "01";
        firstSender.send(uuid, s);
//        firstSender.send2(uuid,s);
        return user;
    }


}
