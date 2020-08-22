package com.springdemo.jpademo.controller;

import com.springdemo.jpademo.entity.Person;
import com.springdemo.jpademo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "person")
public class PerconController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping(path = "addPerson")
    public void addPerson(@RequestBody Person person) {
        personRepository.save(person);
    }

    @GetMapping(path = "deletePerson")
    public void deletePerson(Long id) {
        Person person = new Person();
        person.setId(id);
        personRepository.delete(person);
    }
}