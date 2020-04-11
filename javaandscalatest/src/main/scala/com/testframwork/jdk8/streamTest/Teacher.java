package com.testframwork.jdk8.streamTest;

import lombok.Data;

import java.util.Optional;

@Data
public class Teacher {

    public Optional<String> name;
    public Optional<Integer> age;
    public Optional<Double> income;

    public Teacher() {
    }


    public Teacher(Optional<String> name, Optional<Integer> age) {
        this.name = name;
        this.age = age;
    }
}
