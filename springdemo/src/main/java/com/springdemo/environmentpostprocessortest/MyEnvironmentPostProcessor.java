package com.springdemo.environmentpostprocessortest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * create by sumerian on 2020/6/21
 * <p>
 * desc:允许定制应用的上下文的应用环境优于应用的上下文之前被刷新
 **/
@Component
@Slf4j
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:\\project\\testframwork0607\\springdemo\\src\\main\\java\\com\\springdemo\\environmentpostprocessortest\\demo.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("my",properties);
            environment.getPropertySources().addLast(propertiesPropertySource);
        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException...={}",e);
        } catch (IOException e) {
            log.info("IOException.............={}",e);
        }
    }
}
