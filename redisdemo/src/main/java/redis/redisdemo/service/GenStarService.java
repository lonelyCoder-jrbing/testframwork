package redis.redisdemo.service;

import redis.redisdemo.controller.userstar2.request.GenStarVO;

import java.util.List;
import java.util.Set;

/**
 * create by sumerian on 2020/7/28
 * <p>
 * desc:
 **/
public interface GenStarService {

    Set<String> genAndGetUserStar(GenStarVO genStarVO);

}
