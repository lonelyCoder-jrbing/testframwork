package com.springdemo.applicationeventpublisheraware.entity;

/**
 * create by sumerian on 2020/6/2
 * <p>
 * desc:
 **/
public class User {
    private Integer id;
    private String name;
    private String phoneNum;
    private String email;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", phoneNum=" + phoneNum + ", email=" + email + "]";
    }

}
