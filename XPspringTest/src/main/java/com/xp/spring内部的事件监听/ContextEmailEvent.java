package com.xp.spring内部的事件监听;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @author xupan
 * @date 2021/12/20 00:51
 **/
public class ContextEmailEvent extends ApplicationContextEvent {
	/**
	 * Create a new ContextStartedEvent.
	 *
	 * @param source the {@code ApplicationContext} that the event is raised for
	 *               (must not be {@code null})
	 */
	public ContextEmailEvent(ApplicationContext source) {
		super(source);
	}
}
