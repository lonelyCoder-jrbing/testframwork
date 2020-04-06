package com.springdemo.cycleReferencetest;

import org.springframework.stereotype.Component;

@Component
public class Address {
    private User user;

    public Address(User user) {
        System.out.println("address begin");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
