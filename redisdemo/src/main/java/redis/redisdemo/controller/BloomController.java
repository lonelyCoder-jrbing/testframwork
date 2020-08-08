package redis.redisdemo.controller;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.redisdemo.reidsconfig.BloomFilterConfig;

/**
 * create by sumerian on 2020/8/7
 * <p>
 * desc:
 **/
@RestController
@RequestMapping("/bloom")
public class BloomController {
    @Autowired
    private BloomFilterConfig bloomFilterConfig;

    @GetMapping("/exist")
    public String exist(@RequestParam("name") String name) {
        BloomFilterConfig.BloomResult bloomResult = bloomFilterConfig
                .newBloomFilter(name, 1000, 0.01);
        return bloomResult.toString();
    }
}
