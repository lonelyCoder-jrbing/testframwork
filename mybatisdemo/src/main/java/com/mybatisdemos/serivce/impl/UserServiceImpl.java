package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.dao.userinfodao.IUserMapper;
import com.mybatisdemos.serivce.UserService;
import com.mybatisdemos.vo.CodeMsg;
import com.mybatisdemos.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserMapper userDao;

    @Override
    public ResultVo getAllUsers() {
        List<IUser> allUsers = userDao.getAllUserPage();
        if (allUsers.size() == 0) {
            return ResultVo.error(CodeMsg.SELECT_ERROR);
        } else {
            return ResultVo.success(allUsers);
        }
    }
}
