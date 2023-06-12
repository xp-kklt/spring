package com.xp.aop.post_processor;

import com.xp.aop.cglib.CglibUtils;
import com.xp.aop.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/07/03 23:45
 **/
//这个是我扩展的，不能让程序员扫描，而且程序员也可能不知道我这个东西，所以不能用@Component,只能在配置类上@Import
public class CustomAopBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof UserService) {
			bean = CglibUtils.getProxy();
		}
		return bean;
	}
}
