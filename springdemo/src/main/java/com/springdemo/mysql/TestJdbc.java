package com.springdemo.mysql;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "person")
public class TestJdbc {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @GetMapping(path = "test")
    public void test01() {
        String sql = "select count(1) as count from person where id = ?";
//        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
//        System.out.println("integer" + integer);
        HashMap<String, Object> codemap = new HashMap<>();
        codemap.put("Id", 1);
//        Object[]param = {1};
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, codemap);
        System.out.println("rest:   "+stringObjectMap);

        List<Object> collect = codemap.entrySet().stream().map(el -> el.getValue()).collect(Collectors.toList());
        Object[] obj = new Object[collect.size()];
        for (int i =0;i<collect.size();i++) {
            obj[i] = collect.get(i);
        }
        Integer integer = jdbcTemplate.queryForObject(sql, obj, Integer.class);
        System.out.println("stringobjectMap:   " + integer);
    }

    @Data
    public class person {
        private long id;

    }

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
  objectObjectHashMap.get("1");

    }
}
