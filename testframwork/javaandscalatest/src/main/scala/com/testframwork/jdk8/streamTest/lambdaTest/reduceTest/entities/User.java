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

        private int total = 0;

        private int count = 0;

        public Averager() {
        }


        public double average() {

            return count > 0 ? ((double) total) / count : 0;

        }

       //可以将其看做是consumer
        public void accept(int i) {
            total += i;
            count++;
        }

        public void combine(Averager other) {

            total += other.total;

            count += other.count;

        }

    }
}
