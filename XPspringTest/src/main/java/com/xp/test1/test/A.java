package com.xp.test1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/03/10 17:14
 **/
@Component
public class A {

	@Autowired
	B b222;

	//为什么自动装配下setXxx这样也可以
	public void setB1(B b222) {
		//	this.b = b;
		System.out.println(b222);
		System.out.println("aabbbbbbba");
	}

	public B getB() {
		return b222;
	}

	public void getBb() {
		System.out.println("B的：" + b222);
	}


}
