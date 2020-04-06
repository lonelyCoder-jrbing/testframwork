package com.springdemo.beanpostprocessorTest.annotationTest.Test02;

@Table(value = "t_student")
public class StudentDTO {

    @Collum(value = "id")
    private String id;
    @Collum(value = "name")
    private String name;

    public StudentDTO() {
    }

    public StudentDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
