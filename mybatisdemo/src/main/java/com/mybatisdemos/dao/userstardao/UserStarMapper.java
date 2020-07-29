package com.mybatisdemos.dao.userstardao;

import com.mybatisdemos.controller.userstar.request.GenStarVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * create by sumerian on 2020/7/27
 * <p>
 * desc:
 **/
@Mapper
public interface UserStarMapper {

    boolean insertUserStar(@Param("genStarVO") GenStarVO genStarVO);


    int getUserById(Integer userId);

    void updateUser(@Param("userId")Integer userId);

    void insertUserMeg(@Param("userId")Integer userId);

    void updatePostTable(@Param("postId")Integer postId);

    List<GenStarVO> getStarMap();

    List<Integer> loadUserStarList(@Param("userId")Integer userId);
}
