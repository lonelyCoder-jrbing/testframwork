package redis.redisdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.redisdemo.pojo.User;
import redis.redisdemo.service.CacheTestService;

/**
 * create by sumerian on 2020/7/3
 * <p>
 * desc:
 **/
@RestController
@ResponseBody
@Slf4j
public class SpringCacheController {

    @Autowired
    private CacheTestService cacheTestService;


    @GetMapping(value = "/test1")
    @ResponseBody
    public User getValueBVyKey(@RequestParam String userId) {
        log.info("........getValueByKey={}", userId);
        User user = cacheTestService.getUser(Long.valueOf(userId));
        return user;
    }

    @GetMapping(value = "/test2")
    @ResponseBody
    public void removeFromCache(@RequestParam String userId) {
        log.info("........removeFromCache={}", userId);
        cacheTestService.removeFromCache(Long.valueOf(userId));
        return;
    }

}
