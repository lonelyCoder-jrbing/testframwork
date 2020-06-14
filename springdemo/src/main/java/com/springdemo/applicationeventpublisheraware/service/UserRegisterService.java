package com.springdemo.applicationeventpublisheraware.service;

import com.springdemo.applicationeventpublisheraware.UserRegisterEvent.UserRegisterEvent;
import com.springdemo.applicationeventpublisheraware.entity.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;


//监听机制可以用在：
//比如将mq同步过来的数据要同步在es中和mysql中两份，在插入mysql中的service中，我们可以引入applicationEventPublisher，
//在插入mysql中的时候，同时将该消息发布，在插入es中的service中继承ApplicationListener，重写onApplicationEvent，在该方法中写插入es的逻辑
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
