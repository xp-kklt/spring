package com.xp.aop.cglib;

import com.xp.aop.service.UserService;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xupan
 * @date 2021/07/04 00:07
 **/
public class CglibUtils {

	public static Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserService.class);
		//enhancer.setInterfaces(new Class<?>[] {EnhancedConfiguration.class});
		enhancer.setUseFactory(false);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				System.out.println("this is aop");
				Object result = methodProxy.invokeSuper(o, objects);
				return result;
			}
		});
		//enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
		//enhancer.setStrategy(new BeanFactoryAwareGeneratorStrategy(classLoader));
		//enhancer.setCallbackFilter(CALLBACK_FILTER);
		//enhancer.setCallbackTypes(CALLBACK_FILTER.getCallbackTypes());

		Object proxy = enhancer.create();
		return proxy;
	}
}











