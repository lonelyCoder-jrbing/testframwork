package com.springdemo.jdbcdemo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
 
/**
 * Druid数据库连接池配置文件
 */
@Configuration
public class DruidConfig {
    private static final Logger logger = LoggerFactory.getLogger(DruidConfig.class);
 
    @Value("${spring.datasource.url}")
    private String dbUrl;
 
    @Value("${spring.datasource.username}")
    private String username;
 
    @Value("${spring.datasource.password}")
    private String password;
 
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
 
    @Value("${spring.datasource.initial-size}")
    private int initialSize;
 
    @Value("${spring.datasource.max-active}")
    private int maxActive;
 
    @Value("${spring.datasource.min-idle}")
    private int minIdle;
 
    @Value("${spring.datasource.max-wait}")
    private int maxWait;
 
    /**
     * Druid 连接池配置
     */
    @Bean     //声明其为Bean实例
    public DruidDataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(3);
        try {
            datasource.setFilters("");
        } catch (Exception e) {
            logger.error("druid configuration initialization filter", e);
        }
//        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }
    /**
     * JDBC操作配置
     */
    @Bean(name = "dataOneTemplate")
    public JdbcTemplate jdbcTemplate (@Autowired DruidDataSource dataSource){
        return new JdbcTemplate(dataSource) ;
    }
    /**
     * 装配事务管理器
     */
    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
 
    /**
     * JDBC事务操作配置
     */
    @Bean(name = "txTemplate")
    public TransactionTemplate transactionTemplate (@Autowired DataSourceTransactionManager transactionManager){
        return new TransactionTemplate(transactionManager);
    }
 
    /**
     * 配置 Druid 监控界面
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean srb =
                new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //设置控制台管理用户
        srb.addInitParameter("loginUsername","root");
        srb.addInitParameter("loginPassword","root");
        //是否可以重置数据
        srb.addInitParameter("resetEnable","false");
        return srb;
    }
    @Bean
    public FilterRegistrationBean statFilter(){
        //创建过滤器
        FilterRegistrationBean frb =
                new FilterRegistrationBean(new WebStatFilter());
        //设置过滤器过滤路径
        frb.addUrlPatterns("/*");
        //忽略过滤的形式
        frb.addInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return frb;
    }
}