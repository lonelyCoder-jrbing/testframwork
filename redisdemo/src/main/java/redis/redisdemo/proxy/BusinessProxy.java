package redis.redisdemo.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.redisdemo.cacheaspect.JsonResult;
import redis.redisdemo.cacheaspect.SettingBusiness;

import java.lang.reflect.Method;

/**
 * create by sumerian on 2020/6/15
 * <p>
 * desc: cglib代理方法的使用
 **/

@Configuration
@Slf4j
public class BusinessProxy {

    @Bean
    public SettingBusiness getInsance() {
        log.info("proxy.....start.....");
        final SettingBusiness settingBusiness = new SettingBusiness();
        // 创建cglib核心对象
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(settingBusiness.getClass());
        // 设置回调
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                log.info("SettingBusiness====param====in=={}", args);
                System.out.println("==============");
                Object invoke = method.invoke(settingBusiness, args);
                log.info("SettingBusiness====param====out=={}", invoke);
                return invoke;
            }
        });
        log.info("proxy.....end");
        return (SettingBusiness) enhancer.create();
    }
}
