package com.springdemo.jpademo.repository;

import com.springdemo.jpademo.entity.Users;

public interface UsersRepository {

	public Users findUserById(Integer userid);
}