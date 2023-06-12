package com.xp.circular_reference;

import com.xp.circular_reference.config.AppConfig;
import com.xp.circular_reference.service.OrderService;
import com.xp.circular_reference.service.UserService;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xupan
 * @date 2021/06/24 17:14
 **/
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		//	DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) ac.getBeanFactory();
		//	beanFactory.setAllowCircularReferences(true);
		//	System.out.println(ac.getBean(UserService.class));
		//	System.out.println(ac.getBean(OrderService.class));

		//	ac.getBean(UserService.class).sing();
		//	ac.getBean(OrderService.class).say();
	}
}
