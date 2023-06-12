package com.xp.spring内部的事件监听;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/12/19 22:59
 **/
@Component
public class ContextEmailListener implements ApplicationListener<ContextEmailEvent> {

	@Override
	public void onApplicationEvent(ContextEmailEvent event) {
		System.out.println("我看到他发送邮件了");
	}
}
