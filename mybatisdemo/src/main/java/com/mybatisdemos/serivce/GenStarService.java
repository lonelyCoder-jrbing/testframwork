package com.mybatisdemos.serivce;

import com.mybatisdemos.controller.userstar.request.GenStarVO;

import java.util.List;

/**
 * create by sumerian on 2020/7/27
 * <p>
 * desc:
 **/


public interface GenStarService {

    List<Integer> GenStar(GenStarVO genStarVO);


}
