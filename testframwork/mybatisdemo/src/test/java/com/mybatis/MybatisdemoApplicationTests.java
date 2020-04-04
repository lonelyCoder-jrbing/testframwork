package com.mybatis;

//import com.mybatisdemos.dao.studentinfodao.StudentDao;
import com.mybatisdemos.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class MybatisdemoApplicationTests {
//    @Autowired
//    private StudentDao studentDaos;

    @Test
    void contextLoads() {
    }

    @Test
    void getStudentById() {
          int id= 12;
//        Student studentById = studentDaos.findStudentById(id);

    }

}
