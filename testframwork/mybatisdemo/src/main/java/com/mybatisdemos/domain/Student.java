package com.mybatisdemos.domain;

import lombok.Data;

import java.util.List;

@Data
public class Student {
    private int id;
    private String code;
    private String name;
    private String age;
    private String phoneNo;
    private List<Course> courseList;

    @Data
    public class Course {
        private int id;
        private String name;
        private String teacher;
        private List<Student> studentList;
    }
}
