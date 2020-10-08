package com.springdemo.cycleReferencetest;

import org.springframework.stereotype.Component;

@Component
public class User {

    private Address address;

    public User(Address address) {
        System.out.println("User begin  ...");
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
