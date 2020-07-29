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
//
//    @Autowired
//    private TestService testService;

    @ApiIdempotent
    @PostMapping("/testIdempotence")
    public boolean testIdempotence() {
        return false;
    }
}