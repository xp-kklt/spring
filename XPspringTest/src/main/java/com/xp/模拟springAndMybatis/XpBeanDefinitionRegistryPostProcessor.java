package com.xp.模拟springAndMybatis;

import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author xupan
 * @date 2021/06/16 19:07
 **/
public class XpBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	String packageName;
	Class<Object> mapperInterface;

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		//这里已经有了clazz和packageName，所以可以从bdMap中扫描出来所有的dao
		ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
		//	scanner.registerFilters();
		//扫描的时候已经注册了bd
		//	Set<BeanDefinitionHolder> beanDefinitionHolders = scanner.doScan(packageName);
		//改一下beanClass
//		beanDefinitionHolders.forEach(beanDefinitionHolder -> {
//
//			GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
//			String beanName = beanDefinitionHolder.getBeanName();
//			beanDefinition.setBeanClass(mapperInterface);
//			System.out.println(beanDefinition);
//
//		//	try {
//			//	packageName = "com/xp/模拟springAndMybatis.dao";
//			//	beanName = beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
//			//	Class<?> aClass = Class.forName(packageName + "." + beanName);
//		//		beanDefinition.getPropertyValues().add("clazz",XpDao.class);
////			}catch (ClassNotFoundException e) {
////				e.printStackTrace();
////			}
//
//			//	beanDefinition.getPropertyValues().add("clazz",)
//		//	registry.registerBeanDefinition(beanName,beanDefinition);
//		});
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(XpFactoryBean.class);
		//beanName = beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
		try {
			Class clazz = Class.forName(packageName + "." + "XpDao");
			beanDefinition.getPropertyValues().add("clazz", clazz);
			registry.registerBeanDefinition("xpDao", beanDefinition);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	public void setClazz(Class mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	/*@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	//	ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner()
		GenericBeanDefinition xpBeanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("xpDao");
		xpBeanDefinition.setBeanClass(XpFactoryBean.class);
		//获取出属性
		MutablePropertyValues propertyValues = xpBeanDefinition.getPropertyValues();
		propertyValues.add("clazz", XpDao.class);
	}*/
}
