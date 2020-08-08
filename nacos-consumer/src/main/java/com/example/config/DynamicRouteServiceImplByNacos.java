package com.example.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.concurrent.Executor;

/**
 * create by sumerian on 2020/6/2
 * <p>
 * desc: nacos 动态刷新配置
 **/
@Component
@RefreshScope
public class DynamicRouteServiceImplByNacos implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(DynamicRouteServiceImplByNacos.class);

    @Value("${spring.cloud.nacos.config.refreshable-dataids}")
    private  String dataId;

    @Value("${spring.cloud.nacos.config.group}")
    private  String group;
    @Value("${spring.cloud.nacos.config.server-addr}")
    private  String serverAddr;

    @Value("${service.name:}")
    private String serviceName;


    @Bean
    public String routeServiceInit() {
        logger.info("-------------------------------------------------------------------------------");
        logger.info("serviceName:{}",serviceName);
        logger.info("dataId:{}",dataId);
        logger.info("group:{}",group);
        logger.info("serverAddr:{}",serverAddr);
        dynamicRouteByNacosListener(dataId,group,serverAddr);
        return "success";
    }


    /**
     * 监听Nacos Server下发的动态路由配置
     * @param dataId
     * @param group
     */
    public void dynamicRouteByNacosListener (String dataId, String group,String serverAddr){
        try {
            if (group == null || group == ""){
                logger.info("路由group配置为null");
                return;
            }
            if (dataId == null || dataId == ""){
                logger.info("路由dataId配置为null");
                return;
            }
            ConfigService configService= NacosFactory.createConfigService(serverAddr);
            String content = configService.getConfig(dataId, group, 5000);
            logger.info("nacos初始化监听,{}",content);
            configService.addListener(dataId, group, new Listener()  {
                @Override
                public void receiveConfigInfo(String configInfo) {
                   logger.info("configInfo=={}",configInfo);
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
        } catch (NacosException e) {
            logger.error("初始化nacos监听出错:",e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        dynamicRouteByNacosListener(dataId,group,serverAddr);
    }
}
