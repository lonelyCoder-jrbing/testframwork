package com.mybatisdemos.config;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import java.sql.SQLException;
import java.sql.Statement;

//@EnableAutoConfiguration
//@Data
public class MybatisConfig {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    @Autowired
//    private ComboPooledDataSource dataSource;
//
//    @Bean
//    public SqlSessionFactory getSqlSessionFactory() {
//        try {
//            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//            sqlSessionFactoryBean.setDataSource(dataSource);
//            return sqlSessionFactoryBean.getObject();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Bean
//    public SqlSessionTemplate getSqlSessionTemplate() {
////        return new SqlSessionTemplate(getSqlSessionFactory());
//        String sql = "";
//
//        jdbcTemplate.execute((StatementCallback<Object>) (statement) -> {
//            return statement.execute(sql);
//        });
//        return null;
//
//    }


}
