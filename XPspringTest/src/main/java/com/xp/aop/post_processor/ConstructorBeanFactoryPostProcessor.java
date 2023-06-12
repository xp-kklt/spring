package com.xp.aop.post_processor;

import com.xp.aop.service.B;
import com.xp.aop.service.LoginService;
import com.xp.aop.service.OrderService;
import com.xp.aop.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author xupan
 * @date 2021/07/12 23:42
 **/
public class ConstructorBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		GenericBeanDefinition orderServiceBd =
				(GenericBeanDefinition) beanFactory.getBeanDefinition("orderService");
		// 在bd中设置参数值
		//orderServiceBd.getConstructorArgumentValues().addIndexedArgumentValue(0,beanFactory.getBean(UserService.class));
		/*orderServiceBd.getConstructorArgumentValues()
				.addIndexedArgumentValue(0,"com.xp.aop.service.UserService");
*/
		//orderServiceBd.getConstructorArgumentValues().addGenericArgumentValue(beanFactory.getBean(UserService.class));
		// 自动装配
		orderServiceBd.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		//orderServiceBd.setInstanceSupplier(()->new OrderService(beanFactory.getBean(LoginService.class)));
	}
}
