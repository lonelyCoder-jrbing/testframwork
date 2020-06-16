package redis.redisdemo.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.redisdemo.cacheaspect.JsonResult;
import redis.redisdemo.cacheaspect.SettingBusiness;
import redis.redisdemo.interfaces.Claims;
import redis.redisdemo.interfaces.Insurance;
import java.lang.reflect.Method;
import java.util.List;

/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc:  redis缓存切面，jdk，cglib动态代理 测试controller
 **/


@RestController
@RequestMapping("/redis")
@Slf4j
public class CacheAspectController {

    @Autowired
    private SettingBusiness settingBusiness;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private Claims claims;

    @Autowired
    private Insurance insurance;


    /***
     * 使用缓存切面
     * @return
     */
    @GetMapping("/cacheAspect")
    public List<JsonResult> getValueFromRedis() {
        return settingBusiness.getSettingList("124");
    }

    /*****
     * 验证缓存切面
     * @return
     */
    @GetMapping("/verify")
    public List<JsonResult> verify() {
        String valueGetFromRedis = redisTemplate.opsForValue().get("SettingBusiness_getSettingList_124");
        log.info("valueGetFromRedis:    {}", valueGetFromRedis);
        JsonResult jsonResult = JSON.parseObject(valueGetFromRedis, JsonResult.class);
        log.info("jsonResult:   {}", jsonResult);
        return null;
    }

    /***
     *使用cglib方式代理redis的缓存切面
     */
    @GetMapping("/cglib")
    public List<JsonResult> cglibTest() {
        final SettingBusiness settingBusiness = new SettingBusiness();
        // 创建cglib核心对象
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(settingBusiness.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object invoke = method.invoke(settingBusiness, args);
                return invoke;
            }
        });
        SettingBusiness settingBusinesss = (SettingBusiness) enhancer.create();
        log.info("cglibTest:   {}", settingBusinesss);
        return settingBusinesss.getSettingList("123");
    }

    /***
     * @RequestParam 不能传递对象，只能传递参数类型
     * @RequestBody  只能传递对象，不能传递参数类型，否则，会报json解析错误
     * @param id
     */
    @GetMapping("cglib2")
    public void cglibTest2(@RequestParam Integer id) {
        claims.fixLoss1();
        claims.fixLoss2();
        claims.fixLoss3();
        insurance.buy1();
        insurance.buy2();
        insurance.buy3();
    }


}
