package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.controller.userstar.request.GenStarVO;
import com.mybatisdemos.dao.userstardao.UserStarMapper;
import com.mybatisdemos.serivce.GenStarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create by sumerian on 2020/7/27
 * <p>
 * desc:  点赞的具体实现逻辑，一篇文章一个用户只能点一次赞
 **/
@Service
@Slf4j
public class GenStarServiceImpl implements GenStarService {
    @Autowired
    private UserStarMapper userStarMapper;

    @Override
    public List<Integer> GenStar(GenStarVO genStarVO) {
        log.info("genStarVO:{}", genStarVO);
        //向star表中插入数据，一篇文章一个用户只能点一次赞
        List<GenStarVO> starMap = userStarMapper.getStarMap();//map中key为文章id
        List<String> collect = starMap.stream().map(el -> {
            return String.valueOf(el.getPostId()) + String.valueOf(el.getUserId());
        }).collect(Collectors.toList());
        log.info("collect ==={}", collect);
        String composite = String.valueOf(genStarVO.getPostId()) + String.valueOf(genStarVO.getUserId());

        log.info("composite===={}", composite);

        Map<Integer, Integer> starMap2 = starMap.stream().collect(Collectors.toMap(GenStarVO::getPostId, GenStarVO::getUserId, (v1, v2) -> v2));

        log.info("starMap=={}", starMap2);
        if (!collect.contains(composite)) {
            userStarMapper.insertUserStar(genStarVO);
            //向用户表中插入数据,如果用户表中没有该userId，先插入userId，并且设置点赞数量为0
            int userById = userStarMapper.getUserById(genStarVO.getUserId());
            log.info("userById=={}", userById);
            if (userById > 0) {
                userStarMapper.updateUser(genStarVO.getUserId());
                //向文章表中插入数据
                userStarMapper.updatePostTable(genStarVO.getPostId());
            } else {
                userStarMapper.insertUserMeg(genStarVO.getUserId());
                //向文章表中插入数据
                userStarMapper.updatePostTable(genStarVO.getPostId());
            }
        }
        //查询用户点过赞的文章列表
        List<Integer> list = userStarMapper.loadUserStarList(genStarVO.getUserId());
        return list;
    }
}