package com.mybatisdemos.serivce;

import com.mybatisdemos.domain.MyStudent;
import com.mybatisdemos.domain.Student;

import java.io.IOException;

public interface StudentService {

    Student findStudentById(int id);

    int insert(MyStudent record);

    MyStudent selectOne(int id);
}
