package com.xp.模拟springAndMybatis;

import com.xp.模拟springAndMybatis.config.AppConfig;
import com.xp.模拟springAndMybatis.dao.A;
import com.xp.模拟springAndMybatis.dao.UserDao;
import com.xp.模拟springAndMybatis.dao.XpDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author xupan
 * @date 2021/06/16 19:13
 **/
public class Application {
	public static void main(String[] args) {
		//	ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		//	ac.register(AppConfig.class);
		//	ac.register(UserDao.class);
		//	XpBeanDefinitionRegistryPostProcessor xpBeanDefinitionRegistryPostProcessor = new XpBeanDefinitionRegistryPostProcessor();
		//	ac.addBeanFactoryPostProcessor(xpBeanDefinitionRegistryPostProcessor);
		//	ac.refresh();
		//	ac.getBean(XpDao.class).say();
		//	ac.getBean(XpDao.class).sing();
		System.out.println(ac.getBean(AppConfig.class));
		System.out.println(ac.getBean(A.class));
		System.out.println(ac.getBean(A.class));
	}
}
