package redis.redisdemo.controller.userstar2;

import redis.redisdemo.controller.userstar2.request.GenStarVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.redisdemo.service.GenStarService;

import java.util.List;
import java.util.Set;

/**
 * create by sumerian on 2020/7/27
 * <p>
 * desc: 模拟用户点赞2.0使用redis缓存实现
 **/
@RestController
@RequestMapping(value = "/user")
@Slf4j
public class GenStarController {

    @Autowired
    private GenStarService genStarService;

     //返回用户点过赞的文章列表
    @PostMapping(value = "/star")
    public Set<String> GenStar(@RequestBody GenStarVO genStarVO) {
        //
        Set<String> b = genStarService.genAndGetUserStar(genStarVO);
        return b;
    }


}
