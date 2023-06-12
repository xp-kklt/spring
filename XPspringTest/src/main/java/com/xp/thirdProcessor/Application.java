package com.xp.thirdProcessor;

import com.xp.thirdProcessor.config.AppConfig;
import com.xp.thirdProcessor.service.ChildBean;
import com.xp.thirdProcessor.service.RootBean;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xupan
 * @date 2021/06/24 17:14
 **/
public class Application {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

		ac.register(AppConfig.class);
		//ac.refresh();

		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
		rootBeanDefinition.setBeanClass(RootBean.class);
		//rootBeanDefinition.setBeanClassName("com.xp.thirdProcessor.service.RootBean");
		rootBeanDefinition.getPropertyValues().addPropertyValue("name", "hhh");
		rootBeanDefinition.getPropertyValues().addPropertyValue("age", 30);

		GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
		genericBeanDefinition.setBeanClass(ChildBean.class);
		genericBeanDefinition.setParentName("root");
		//	genericBeanDefinition.getPropertyValues().addPropertyValue("age",20);
		genericBeanDefinition.getPropertyValues().addPropertyValue("name", "xiaoming");

		ac.registerBeanDefinition("root", rootBeanDefinition);
		ac.registerBeanDefinition("child", genericBeanDefinition);

		ac.refresh();
		//ac.start();

		System.out.println(ac.getBean(ChildBean.class).toString());
		//ac.stop();
	}
}
