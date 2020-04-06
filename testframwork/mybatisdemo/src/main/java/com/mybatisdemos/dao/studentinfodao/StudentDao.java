package com.mybatisdemos.dao.studentinfodao;
import com.mybatisdemos.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StudentDao {

    @Select({"select * from student where id = #{id}"})
//    @Results(id = "id",
//            value = {
//                    @Result(property = "courseList", column = "id", many = @Many(select = "com.mybatisdemos.dao.studentinfodao.CourseDao.selectCoursesByStudentId"))
//            }
//    )
    Student findStudentById(@Param("id") int id);



}
