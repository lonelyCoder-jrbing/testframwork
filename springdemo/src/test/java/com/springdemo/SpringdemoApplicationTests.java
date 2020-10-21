package com.springdemo;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class SpringdemoApplicationTests {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() {
        String sql  = "select count(1) from person where id = 1";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println("integer:"+integer);
    }

}
