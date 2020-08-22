package com.springdemo.jpademo.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_users")
@Data
public class Users implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//strategy=GenerationType.IDENTITY 自增长
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_age")
	private Integer userAge;


	@Override
	public String toString() {
		return "Users [userid=" + userId + ", username=" + userName + ", userage=" + userAge + "]";
	}
	
}