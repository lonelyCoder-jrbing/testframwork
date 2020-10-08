package com.springdemo.jpademo.repository.impl;

import com.springdemo.jpademo.entity.Users;
import com.springdemo.jpademo.repository.UsersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsersDaoImpl implements UsersRepository {

    @PersistenceContext(name = "entityManagerFactory")
    private EntityManager em;

    @Override
    public Users findUserById(Integer userid) {
        System.out.println("MyRepository......");
        return this.em.find(Users.class, userid);
    }

}