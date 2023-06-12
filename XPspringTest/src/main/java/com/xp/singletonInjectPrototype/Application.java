package com.xp.singletonInjectPrototype;

import com.xp.singletonInjectPrototype.config.AppConfig;
import com.xp.singletonInjectPrototype.service.A;
import com.xp.singletonInjectPrototype.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xupan
 * @date 2021/06/24 17:14
 **/
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		OrderService bean = ac.getBean(OrderService.class);
		bean.getUser();
		OrderService bean1 = ac.getBean(OrderService.class);
		bean1.getUser();
		System.out.println("***************");
		A a1 = ac.getBean(A.class);
		a1.getB();
		A a2 = ac.getBean(A.class);
		a2.getB();
		//	X bean1 = ac.getBean(X.class);
		//bean.ox();
	}
}
