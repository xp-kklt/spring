package com.xp.populateProperty;

import com.xp.populateProperty.config.AppConfig;
import com.xp.populateProperty.service.OrderService;
import com.xp.populateProperty.service.X;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xupan
 * @date 2021/06/24 17:14
 **/
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		OrderService bean = ac.getBean(OrderService.class);
		//	X bean1 = ac.getBean(X.class);
		//bean.ox();
	}
}
