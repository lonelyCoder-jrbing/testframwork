//package com.mybatisdemos.config;
//
//
//import com.mybatisdemos.intercepters.page.PageInterceptor;
//import com.mybatisdemos.intercepters.sqlExecuteTimeCount.SqlExecuteTimeCountInterceptor;
//import com.mybatisdemos.intercepters.sqlExecuteTimeCount.SqlExecuteTimeCountInterceptor2;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
///**
// * mybatis配置
// */
//@Configuration
//public class MybatisConfiguration {
////https://blog.csdn.net/wuyuxing24/article/details/89343951
//    @Bean
//    public PageInterceptor pageInterceptor() {
//        PageInterceptor interceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty(PageInterceptor.PROPERTIES_KEY_DATABASE_TYPE, PageInterceptor.DATABASE_TYPE_MYSQL);
//        properties.setProperty(PageInterceptor.PROPERTIES_KEY_PAGE_EXPRESSION_MATCHING, ".*Page.*");
//        properties.setProperty(PageInterceptor.PROPERTIES_KEY_COUNT_SUFFIX, "_COUNT");
//        interceptor.setProperties(properties);
//        return interceptor;
//    }
//
//    @Bean
//    public SqlExecuteTimeCountInterceptor timeCountInterceptor() {
//        SqlExecuteTimeCountInterceptor interceptor = new SqlExecuteTimeCountInterceptor();
//        return interceptor;
//    }
//
//    @Bean
//    public SqlExecuteTimeCountInterceptor2 timeCountInterceptor2(){
//        return  new SqlExecuteTimeCountInterceptor2();
//    }
//}
