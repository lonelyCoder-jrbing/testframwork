package com.mybatisdemos.dao.studentinfodao.impl;

import com.mybatisdemos.dao.studentinfodao.StudentDao;
import com.mybatisdemos.dao.studentinfodao.TestSelectOne;
import com.mybatisdemos.domain.MyStudent;
import com.mybatisdemos.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
@Slf4j
@Component
public class StudentDaoimpl implements TestSelectOne {
    @Autowired
    private SqlSessionTemplate template;
    @Override
    public MyStudent findStudentByid(int id) {
        MyStudent s = template.selectOne("com.mybatisdemos.dao.studentinfodao.MyStudentMapper.selectStudentByid", 1);
         log.info("s:  ",s);
       return s;
    }
}
