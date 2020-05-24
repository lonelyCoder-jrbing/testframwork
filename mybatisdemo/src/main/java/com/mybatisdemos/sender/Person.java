package com.mybatisdemos.sender;

public class Person {
    private String userName;
    private Integer age;
    private String gender;

    public Person(String userName, Integer age, String gender) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
