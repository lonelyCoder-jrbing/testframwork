package com.mybatisdemos.dao.studentinfodao;

import com.mybatisdemos.domain.Student;
import com.mybatisdemos.domain.Student.Course;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//@Mapper
public interface CourseDao {
    @Select("select * from course where id =#{studentId}")
    List<Course> selectCoursesByStudentId(@Param("studentId") int studentId);
}
