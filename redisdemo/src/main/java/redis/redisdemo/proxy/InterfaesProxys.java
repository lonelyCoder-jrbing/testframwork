package redis.redisdemo.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.redisdemo.cacheaspect.SettingBusiness;
import redis.redisdemo.interfaces.Claims;
import redis.redisdemo.interfaces.Impl.ClaimsImpl;
import redis.redisdemo.interfaces.Impl.InsuranceImpl;
import redis.redisdemo.interfaces.Insurance;

import java.lang.reflect.Method;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
@Slf4j
@Configuration
public class InterfaesProxys {

    @Bean
    public Claims getInsance1() {
        log.info("proxy.....start.....");
        final Claims claims = new ClaimsImpl();
        // 创建cglib核心对象
        Enhancer enhancer = new Enhancer();
        // 设置多个接口
        enhancer.setSuperclass(claims.getClass());
        // 设置回调
        Callback[] callbacks = {
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        log.info("SettingBusiness====param====in=={}", args);
                        System.out.println("==============");
                        Object invoke = method.invoke(claims, args);
                        log.info("SettingBusiness====param====out=={}", invoke);
                        return invoke;
                    }
                }
        };
        enhancer.setCallbacks(callbacks);
        log.info("proxy.....end");
        return (Claims) enhancer.create();
    }

    @Bean
    public Insurance getInsance2() {
        log.info("proxy.....start.....");
//        final SettingBusiness settingBusiness = new SettingBusiness();
        final Insurance insurance = new InsuranceImpl();
        // 创建cglib核心对象
        Enhancer enhancer = new Enhancer();
        // 设置多个接口
//        Class[] classes = {claims.getClass(), insurance.getClass()};
//        enhancer.setInterfaces(classes);
        enhancer.setSuperclass(insurance.getClass());
        // 设置回调
        Callback[] callbacks = {
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                        log.info("SettingBusiness====param====in=={}", args);
                        System.out.println("==============");
                        Object invoke = method.invoke(insurance, args);
                        log.info("SettingBusiness====param====out=={}", invoke);
                        return invoke;
                    }
                }
        };
        enhancer.setCallbacks(callbacks);
        log.info("proxy.....end");
        return (Insurance) enhancer.create();
    }
}
