package redis.redisdemo.cacheaspect;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.redisdemo.annotation.RedisCache;
import redis.redisdemo.annotation.RedisCachetAttribute;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * SettingBusiness.
 */
//@Component
@Slf4j
public class SettingBusiness {

    @RedisCachetAttribute(key = "T(com.g2.order.server.vo.CommonConst)" +
            ".SETTING_JSON_KEY" +
            " + #code"
            , expireSeconds = 30
    )
    public JsonResult getSetting(String code) {
        return new JsonResult("234");
    }

    //    @RedisCachetAttribute(key = "T(com.g2.order.server.vo.CommonConst)" +
//            ".SETTING_LIST_KEY" +
//            " + #code"
//            , expireSeconds = 30
//    )
//    @RedisCache(time = 10, timeunit = TimeUnit.DAYS)
    public List<JsonResult> getSettingList(String code) {
           log.info("i am come in.....");
        return Arrays.<JsonResult>asList(new JsonResult("234"), new JsonResult("345"));
    }

    @RedisCachetAttribute(key = "T(com.g2.order.server.vo.CommonConst)" +
            ".SETTING_MAP_KEY" +
            " + #code"
            , expireSeconds = 30
    )
    public Map<String, JsonResult> getSettingMap(String code) {
        return ImmutableMap.of(code, new JsonResult("234"), "abc", new JsonResult("abc234"));
    }
}