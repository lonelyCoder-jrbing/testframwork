package com.springdemo.applicationeventpublisheraware.eventlistener;

import com.springdemo.applicationeventpublisheraware.UserRegisterEvent.UserRegisterEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
 
@Component
public class EventListener implements ApplicationListener<UserRegisterEvent> {
	@Override
	public void onApplicationEvent(UserRegisterEvent event) {
		//发邮件
		System.out.println("正在发送邮件至： " + event.getUser().getEmail());
		
		//发短信
		System.out.println("正在发短信到： " + event.getUser().getPhoneNum());		
	}
}
