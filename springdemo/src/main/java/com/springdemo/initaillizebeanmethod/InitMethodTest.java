package com.springdemo.initaillizebeanmethod;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * create by sumerian on 2020/5/4
 * <p>
 * desc:
 * 指定组建的init方法和destroy的几种方法
 * 1：在配置类中 @Bean(initMethod = "init",destroyMethod = "destory")注解指定
 * 2：实现InitializingBean接口重写其afterPropertiesSet方法，实现DisposableBean接口重写destroy方法
 * 3：利用java的JSR250规范中的@PostConstruct标注在init方法上，@PreDestroy标注在destroy注解上
 **/
@Component
@Data
public class InitMethodTest {


    @Data
    public class Car {
        private Car car;
        private String name;
        private Double price;

        public Car() {
            System.out.println("Car's Constructor..");
        }

        public void init() {
            this.name = "das-auto";
            this.price =120000.00;

            car = new Car();

            System.out.println("Car's Init...");
        }

        public void destory() {
            car = null;

            System.out.println("Car's Destroy...");
        }

    }

    private class Config {

        @Bean(initMethod = "init", destroyMethod = "destory")
        public Car car() {
            return new Car();
        }
    }
}
