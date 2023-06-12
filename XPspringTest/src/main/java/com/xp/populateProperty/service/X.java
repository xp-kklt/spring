package com.xp.populateProperty.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

/**
 * @author xupan
 * @date 2021/07/26 15:16
 **/
@Component
//@Primary
public class X implements A {

	/*int years;
	String ultimateAnswer;

	@ConstructorProperties({"100", "ultimateAnswer"})
	public X(int years, String ultimateAnswer){
		System.out.println("x");
		this.years = years;
		this.ultimateAnswer = ultimateAnswer;
	}*/
	@Override
	public void say() {
		System.out.println("im x");
	}
}
