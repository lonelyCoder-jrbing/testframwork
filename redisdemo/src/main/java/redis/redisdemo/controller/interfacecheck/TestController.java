package redis.redisdemo.controller.interfacecheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.redisdemo.annotation.ApiIdempotent;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    //添加 @ApiIdempotent 利用拦截器获取注解，拿到请求的头，获取header信息
    @ApiIdempotent
    @PostMapping("/testIdempotence")
    public boolean testIdempotence() {
        return true;
    }
}