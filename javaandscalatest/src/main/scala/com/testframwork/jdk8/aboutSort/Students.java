package com.testframwork.jdk8.aboutSort;

import com.testframwork.jdk8.streamTest.Student;
import lombok.Data;

/**
 * create by sumerian on 2020/5/5
 * <p>
 * desc:
 **/
@Data
public class Students implements Comparable<Students> {
    private Integer id;
    private int age;
    private String name;

    public Students(Integer id, int age, String name) {
        this.age = age;
        this.name = name;
        this.id = id;

    }


    @Override
    public int compareTo(Students o) {
        return this.getAge() - o.getAge();
    }
}
