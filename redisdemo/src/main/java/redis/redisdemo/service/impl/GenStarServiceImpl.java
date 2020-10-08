package redis.redisdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.redisdemo.controller.userstar2.request.GenStarVO;
import redis.redisdemo.service.GenStarService;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * create by sumerian on 2020/7/28
 * <p>
 * desc: 使用redis实现点赞功能
 **/
@Service
@Slf4j
public class GenStarServiceImpl implements GenStarService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Set<String> genAndGetUserStar(GenStarVO genStarVO) {
        //一篇文章一个用户只能点一次赞，不能多次点赞
        //键为postid 加上userid， 值为userid
        BoundHashOperations<String, Object, Object> starHash = redisTemplate.boundHashOps("star");
        BoundHashOperations<String, Object, Object> userHash = redisTemplate.boundHashOps("User");
        BoundHashOperations<String, Object, Object> postHash = redisTemplate.boundHashOps("post");

        if (starHash.get(String.valueOf(genStarVO.getPostId()) + " " + String.valueOf(genStarVO.getUserId())) == null) {
            //插入点赞表
            starHash.put(String.valueOf(genStarVO.getPostId()) + " " + String.valueOf(genStarVO.getUserId()), String.valueOf(genStarVO.getUserId()));
            //更新用户表
            if (userHash.get(String.valueOf(genStarVO.getUserId())) == null) {
                userHash.put(String.valueOf(genStarVO.getUserId()), "1");
            } else {
                String num = (String) userHash.get(String.valueOf(genStarVO.getUserId()));
                String newValue = String.valueOf(Integer.valueOf(num) + 1);
                userHash.put(String.valueOf(genStarVO.getUserId()), newValue);
            }
            //更新文章表
            if (postHash.get(String.valueOf(genStarVO.getPostId())) != null) {
                String value = (String) postHash.get(String.valueOf(genStarVO.getPostId()));
                log.info("post value-->{}",value);
                String newValue = String.valueOf(Integer.valueOf(value) + 1);
                log.info("post newValue-->{}",newValue);
                postHash.put(String.valueOf(genStarVO.getPostId()), newValue);
            }else {
                //初始化文章表
                postHash.put(String.valueOf(genStarVO.getPostId()), "1");
            }
        }
        //根据用户id查询用户点赞列表
        Set<Object> starHashkeys = starHash.keys();
        log.info("starHashkeys==={}",starHashkeys);

        log.info("userid--Starnum->{}",userHash.get(String.valueOf(genStarVO.getUserId())));

        log.info("postid--->starNum---{},nums{}",postHash.get(String.valueOf(genStarVO.getPostId())),postHash.values());

        Set<String> starSet = starHashkeys
                .stream()
                .map(el->(String)el)
                .filter(el -> el.split(" ")[1].equals(String.valueOf(genStarVO.getUserId())))
                .map(el -> el.split(" ")[0])
                .collect(Collectors.toSet());
        log.info("starSet=={}",starSet);
        return starSet;
    }
}
