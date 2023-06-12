package com.xp.lifecycle;

import com.xp.lifecycle.config.AppConfig;
import com.xp.lifecycle.post_processor.XpBeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xupan
 * @date 2021/08/06 11:53
 **/
public class Application {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		//	ac.addBeanFactoryPostProcessor(new XpBeanFactoryPostProcessor());
		ac.refresh();
		ac.close();

	}
}
