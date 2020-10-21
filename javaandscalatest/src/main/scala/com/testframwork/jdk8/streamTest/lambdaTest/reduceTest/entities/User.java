package com.testframwork.jdk8.streamTest.lambdaTest.reduceTest.entities;

public class User {

    public int id;

    public String name;

    public int age;

    public Sex gender;

    public User(int id, String name, int age, Sex gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public enum Sex {
        FEMALE,

        MALE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public static class Averager {
        //总的年龄
        private int total = 0;
        //学生的总人数
        private int count = 0;

        public Averager() {
        }


        public double average() {

            return count > 0 ? ((double) total) / count : 0;

        }

       //可以将其看做是consumer  每一次都是新创建一个user，然后从管道中流过来
        public void accept(int i) {
            total += i;
            System.out.println("total:  "+total);
            count++;
        }

        //流到最后是将每一个user对象中的年龄相加起来。
        public void combine(Averager other) {

            total += other.total;
            System.out.println("combine total:  "+total);
            count += other.count;

        }

    }
}
