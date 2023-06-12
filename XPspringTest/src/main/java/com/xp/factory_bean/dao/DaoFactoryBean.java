package com.xp.factory_bean.dao;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/08/19 21:30
 **/
@Component
public class DaoFactoryBean implements FactoryBean {
	Object object;

	@Override
	public Object getObject() throws Exception {
		object = new TestService();
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return object.getClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
