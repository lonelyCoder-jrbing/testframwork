package com.mybatisdemos.controller.studentinfo;

import com.mybatisdemos.domain.MyStudent;
import com.mybatisdemos.domain.Student;
import com.mybatisdemos.serivce.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "getStudentById")
    public Student getStudentById(int id) {

        Student studentById = studentService.findStudentById(id);
        return studentById;
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public void insertStudent(@RequestBody MyStudent student) {
        studentService.insert(student);
    }

    @GetMapping(value = "/testSelectOne")
    @ResponseBody
    public MyStudent testSelectOne(int id) {
      return   studentService.selectOne(id);
    }
}
