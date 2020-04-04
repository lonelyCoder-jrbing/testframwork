package com.mybatisdemos.serivce.impl;

import com.mybatisdemos.dao.studentinfodao.MyStudentMapper;
//import com.mybatisdemos.dao.studentinfodao.StudentDao;
import com.mybatisdemos.dao.studentinfodao.StudentDao;
import com.mybatisdemos.domain.MyStudent;
import com.mybatisdemos.domain.Student;
import com.mybatisdemos.serivce.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
   private StudentDao studentDao;
    @Autowired
    private MyStudentMapper studentMapper;

    @Override
    public Student findStudentById(int id) {
        Student studentById = studentDao.findStudentById(id);
        return studentById;
//    return null;

    }


    @Override
    public int insert(MyStudent record) {
      return   studentMapper.insert(record);

    }
}