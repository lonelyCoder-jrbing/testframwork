package redis.redisdemo.cacheaspect;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import redis.redisdemo.annotation.RedisCache;
import redis.redisdemo.annotation.RedisCachetAttribute;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc:  redis缓存切面使用示例
 **/
@Service
@Slf4j
@Order(4)
public class SettingBusiness {

    @RedisCache(time = 10, timeunit = TimeUnit.DAYS)
    public List<JsonResult> getSettingList(String code) {
           log.info("i am come in.....");
        return Arrays.<JsonResult>asList(new JsonResult("234"), new JsonResult("345"));
    }

    public List<JsonResult> getSettingList2(String code) {
        log.info("i am come in.....");
        return Arrays.<JsonResult>asList(new JsonResult("123"), new JsonResult("567"));
    }

}