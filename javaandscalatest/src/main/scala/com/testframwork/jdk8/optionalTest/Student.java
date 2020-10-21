package com.testframwork.jdk8.optionalTest;

import lombok.Data;

import java.util.Optional;

@Data
public class Student {

    private Optional<String> name;
    private Optional<Integer> age;
    private Optional<Boolean> man;


}
