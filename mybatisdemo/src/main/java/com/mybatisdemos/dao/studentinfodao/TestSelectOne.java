package com.mybatisdemos.dao.studentinfodao;

import com.mybatisdemos.domain.MyStudent;
import com.mybatisdemos.domain.Student;

import java.io.IOException;

public interface TestSelectOne {
    MyStudent findStudentByid(int id);
}
