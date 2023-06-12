package com.xp.singletonInjectPrototype.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/08/09 16:39
 **/
@Component
@Scope("singleton")
public abstract class A {

	//spring容器用cglib实现这个抽象方法
	// 可以使用@Lookup("b")或者@Lookup，@Lookup通过返回值来推测构造的对象类型
	//@Lookup("b")
	/*@Lookup
	public abstract X createX();*/

	@Lookup
	public abstract B createB();

	public void getB() {
		//B b = createB();
		X x = createB();
		System.out.println(x.getClass().getSimpleName());
		System.out.println(x.hashCode());
	}
}
