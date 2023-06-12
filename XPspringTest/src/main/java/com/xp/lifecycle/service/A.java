package com.xp.lifecycle.service;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/08/10 14:50
 **/
//@Scope("prototype")
@Component
public class A {

	public A() {
		System.out.println("A");
	}

	public void initaaa() {
		System.out.println("这是A的初始化方法");
	}

	public void destroyA() {
		System.out.println("这是A的销毁方法");
	}

}
