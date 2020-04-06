package com.mybatisdemos.dao.studentinfodao;

import com.mybatisdemos.domain.Student;

import java.io.IOException;

public interface TestSelectOne {
    Student findStudentByid(int id) throws IOException;
}
