package com.mybatisdemos.serivce.impl;

import com.alibaba.fastjson.JSON;
import com.mybatisdemos.dao.studentinfodao.UsersMapper;
//import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.dao.userinfodao.IUserMapper;
import com.mybatisdemos.dao.userinfodao.UserLoginPOMapper;
import com.mybatisdemos.domain.User;
import com.mybatisdemos.domain.UserLoginPO;
import com.mybatisdemos.rabbitmq.sender.FirstSender;
import com.mybatisdemos.serivce.UserService;
import com.mybatisdemos.vo.CodeMsg;
import com.mybatisdemos.vo.ResultVo;
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

    @Autowired
    private UserLoginPOMapper userLoginPOMapper;

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

    @Override
    public User selectUserById(User user) {
        User user1 = usersMapper.selectUserById(user);
        return user1;
    }

    @Override
    public int insertbatch(List<User> userList) {
        return usersMapper.insertBatch(userList);
    }

    @Override
    public int updatebatch(List<User> userList) {
        return usersMapper.updateBatch(userList);
    }

    @Override
    public int insertUserbatch(List<UserLoginPO> userList) {

        return userLoginPOMapper.insertUserBatch(userList);
    }


}
