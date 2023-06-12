package com.xp.模拟springAndMybatis;

import org.springframework.beans.factory.FactoryBean;


/**
 * @author xupan
 * @date 2021/06/16 18:52
 **/
public class XpFactoryBean implements FactoryBean {
	//	Class clazz;
	@Override
	public Object getObject() throws Exception {
	/*	ClassLoader classLoader = this.getClass().getClassLoader();
		Object o = Proxy.newProxyInstance(classLoader, new Class[]{clazz}, new XpInvocationHandler());
		return o;*/
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		//	return clazz;
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setClazz(Class clazz) {
		//	this.clazz = clazz;
	}
}