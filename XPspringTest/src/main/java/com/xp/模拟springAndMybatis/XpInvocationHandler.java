package com.xp.模拟springAndMybatis;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author xupan
 * @date 2021/06/16 18:54
 **/
public class XpInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("hhhhh");
		//	Object invoke = method.invoke(proxy, args);
		//	System.out.println(invoke);
		//	System.out.println(proxy.toString());
		//	System.out.println(args[0]);
		System.out.println(method.getName());
		if (method.getName().equals("say")) {
			System.out.println("say a word");
		} else if (method.getName().equals("sing")) {
			System.out.println("sing a song");
		}
		Select annotation = method.getAnnotation(Select.class);
		String[] value = annotation.value();
		System.out.println(value[0]);
		return null;
	}
}
