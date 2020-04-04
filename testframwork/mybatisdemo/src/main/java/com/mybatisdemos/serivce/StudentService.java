package com.mybatisdemos.serivce;

import com.mybatisdemos.domain.MyStudent;
import com.mybatisdemos.domain.Student;

public interface StudentService {

    Student findStudentById(int id);

    int insert(MyStudent record);
}
