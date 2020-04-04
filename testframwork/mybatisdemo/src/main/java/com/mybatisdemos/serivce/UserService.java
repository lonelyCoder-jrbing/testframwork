package com.mybatisdemos.serivce;

import com.mybatisdemos.domain.IUser;
import com.mybatisdemos.intercepters.page.PageView;
import com.mybatisdemos.vo.ResultVo;

public interface UserService {
    public ResultVo getAllUsers();

}
