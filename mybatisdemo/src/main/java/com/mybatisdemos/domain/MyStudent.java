package com.mybatisdemos.domain;

public class MyStudent {
    private Integer id;

    private String code;

    private String name;

    private Integer age;

    private String phoneno;

    public MyStudent(Integer id, String code, String name, Integer age, String phoneno) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.age = age;
        this.phoneno = phoneno;
    }

    public MyStudent() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }
}