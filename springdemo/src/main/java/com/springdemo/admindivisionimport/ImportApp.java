package com.springdemo.admindivisionimport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springdemo.admindivisionimport")
public class ImportApp {

    public static void main(String[] args) {
        SpringApplication.run(ImportApp.class, args);
    }


}
