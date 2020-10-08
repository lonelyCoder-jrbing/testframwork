package com.springdemo.ehcachedemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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