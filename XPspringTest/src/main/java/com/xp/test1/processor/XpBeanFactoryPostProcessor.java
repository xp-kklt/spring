package com.xp.test1.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/03/10 23:40
 **/
@Component
public class XpBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition xpService = (GenericBeanDefinition) beanFactory.getBeanDefinition("xpService");
		// xpService.setBeanClass(UserService.class);
		xpService.setAutowireMode(1);
		System.out.println(xpService.getAutowireMode());
	}
}
