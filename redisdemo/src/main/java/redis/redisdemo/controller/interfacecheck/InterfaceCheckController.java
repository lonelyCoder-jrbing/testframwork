package redis.redisdemo.controller.interfacecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;
import redis.redisdemo.service.TokenService;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc: 使用redis+token的方式进行接口幂等性的校验
 **/


@RestController
@RequestMapping("/token")
public class InterfaceCheckController {


    //在需要实现幂等性接口的上一个页面上调用这个接口
    @Autowired
    private TokenService tokenService;

    @GetMapping("/get")
    public String token() {
        return tokenService.createToken();
    }

}
