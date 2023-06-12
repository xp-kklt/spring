package com.xp.spring内部的事件监听;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/12/20 01:02
 **/
@Component
public class MailBean {

	@Autowired
	private ApplicationContext applicationContext;

	public void sendEmail() {
		System.out.println("我要发送邮件");
		applicationContext.publishEvent(new ContextEmailEvent(applicationContext));
	}
}
