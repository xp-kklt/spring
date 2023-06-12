package com.xp.factory_bean;

import com.xp.factory_bean.config.AppConfig;
import com.xp.factory_bean.dao.DaoFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xupan
 * @date 2021/08/06 11:53
 **/
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();

		DaoFactoryBean bean = (DaoFactoryBean) ac.getBean("daoFactoryBean");
		System.out.println(bean);

	}
}
