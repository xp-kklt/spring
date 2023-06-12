package com.xp.aop;

import com.xp.aop.config.AppConfig;
import com.xp.aop.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xupan
 * @date 2021/06/24 17:14
 **/
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		OrderServiceInterace orderService = ac.getBean(OrderServiceInterace.class);
		System.out.println(ac.getBean(OrderServiceInterace.class));
		//System.out.println(ac.getBean(UserService.class, "xxx"));
		orderService.testAop();
		//System.out.println(orderService);
		ac.getBean(A.class);
		ac.getBean(B.class);
	}
}
