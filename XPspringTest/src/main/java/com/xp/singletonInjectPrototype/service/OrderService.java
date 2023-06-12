package com.xp.singletonInjectPrototype.service;

import com.xp.populateProperty.service.A;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/07/26 15:12
 **/
@Component
@Scope("singleton")
public class OrderService implements ApplicationContextAware {

	ApplicationContext applicationContext;

	/*@Autowired
	UserService userService;*/

	public void getUser() {
		UserService bean = applicationContext.getBean(UserService.class);
		System.out.println(bean.hashCode());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
}
