package pojo;

import java.io.Serializable;

public class Student implements Serializable {


    private static final long serialVersionUID = -3275378188512857880L;
    private String id;
    private Integer age;
    private String name;

    public Student(String id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
