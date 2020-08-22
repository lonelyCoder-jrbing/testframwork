package com.springdemo.jpademo.repository;

import com.springdemo.jpademo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {





}