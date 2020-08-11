package com.mybatisdemos.controller.userstar;

import com.mybatisdemos.controller.userstar.request.GenStarVO;
import com.mybatisdemos.serivce.GenStarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * create by sumerian on 2020/7/27
 * <p>
 * desc: 模拟用户点赞
 **/
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class GenStarController {

    @Autowired
    private GenStarService genStarService;

     //返回用户点过赞的文章列表
    @PostMapping(value = "/star")
    public List<Integer> GenStar(@RequestBody GenStarVO genStarVO) {
        //
        List<Integer>  b = genStarService.GenStar(genStarVO);
        return b;
    }


}
