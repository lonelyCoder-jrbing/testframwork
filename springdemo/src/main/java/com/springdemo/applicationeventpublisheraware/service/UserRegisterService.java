package com.springdemo.applicationeventpublisheraware.service;

import com.springdemo.applicationeventpublisheraware.UserRegisterEvent.UserRegisterEvent;
import com.springdemo.applicationeventpublisheraware.entity.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
 
@Service
public class UserRegisterService implements ApplicationEventPublisherAware {
	
	private ApplicationEventPublisher applicationEventPublisher;
	
	public boolean register(User user) {
		
		//用户注册
		System.out.println("[service]用户["  + user + "]注册成功！");
		
		//消息发布
		applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user));
		
		return true;
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
		
	}
}
