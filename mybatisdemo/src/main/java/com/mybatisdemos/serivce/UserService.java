package com.mybatisdemos.serivce;

import com.mybatisdemos.domain.User;
import com.mybatisdemos.vo.ResultVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    public ResultVo getAllUsers();

    List<Map<String, String>> selectByList(List<Integer>list);

   User selectByid(String id);
}
