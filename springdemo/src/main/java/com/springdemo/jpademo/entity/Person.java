package com.springdemo.jpademo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * create by sumerian on 2020/8/22
 * <p>
 * desc:
 **/
@Entity
@Getter
@Setter
@Table(name = "t_person")
public class Person {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = true, length = 20)
    private String name;

    @Column(name = "agee", nullable = true, length = 4)
    private int age;



}
