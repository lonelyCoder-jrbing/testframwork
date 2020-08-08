package com.springdemo.applicationeventpublisheraware.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.springdemo.applicationeventpublisheraware.entity.User;
import com.springdemo.applicationeventpublisheraware.service.UserRegisterService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;


@RestController
@RequestMapping("/user")
public class UserRegisterController {	
	@Autowired
	private UserRegisterService userRegisterService;
	@RequestMapping("/register")
	public String register( @RequestBody User user) throws InterruptedException {
		Semaphore semaphore = new Semaphore(5,true);
		semaphore.acquire();
		RateLimiter limiter = RateLimiter.create(2.0); // 每秒不超过2个任务被提交
		limiter.acquire(); // 请求RateLimiter
		//进行注册
		userRegisterService.register(user);
		//do something here
		semaphore.release();
		return "[controller]注册用户成功！";

	}	
}
