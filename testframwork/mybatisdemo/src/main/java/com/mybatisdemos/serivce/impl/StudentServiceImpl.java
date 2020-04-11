package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.dao.studentinfodao.MyStudentMapper;
//import com.mybatisdemos.dao.studentinfodao.StudentDao;
import com.mybatisdemos.dao.studentinfodao.StudentDao;
import com.mybatisdemos.dao.studentinfodao.TestSelectOne;
import com.mybatisdemos.domain.MyStudent;
import com.mybatisdemos.domain.Student;
import com.mybatisdemos.serivce.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    @Autowired
   private StudentDao studentDao;

    @Autowired
    private TestSelectOne testSelectOne;

    @Autowired
    private MyStudentMapper studentMapper;

    @Override
    public Student findStudentById(int id) {
        Student studentById = studentDao.findStudentById(id);
        return studentById;
    }


    @Override
    public int insert(MyStudent record) {
      return   studentMapper.insert(record);

    }

    @Override
    public MyStudent selectOne(int id) {

        return testSelectOne.findStudentByid(id);
    }


}
