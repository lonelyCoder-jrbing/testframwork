package com.mybatisdemos.dao.studentinfodao.impl;

import com.mybatisdemos.dao.studentinfodao.StudentDao;
import com.mybatisdemos.dao.studentinfodao.TestSelectOne;
import com.mybatisdemos.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
@Slf4j
public class StudentDaoimpl implements TestSelectOne {
    @Override
    public Student findStudentByid(int id) throws IOException {
        String resource = "mappers/myStudentMapper.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();

        Student s = sqlSession.selectOne("com.mybatisdemos.dao.studentinfodao.MyStudentMapper.selectStudentByid", 1);
         log.info("s:  ",s);
       return s;
    }
}
