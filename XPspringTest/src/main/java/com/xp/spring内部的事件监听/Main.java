package com.xp.spring内部的事件监听;

import com.xp.lifecycle.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @author xupan
 * @date 2021/12/19 23:00
 **/
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Appconfig.class);
		MailBean bean = ac.getBean(MailBean.class);
		ac.start();
		bean.sendEmail();
	}
}
