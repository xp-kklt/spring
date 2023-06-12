package com.xp.populateProperty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/07/26 15:12
 **/
@Component
public class OrderService {


	/*public OrderService(){

	}*/

	public OrderService(A b, UserService userService) {
		System.out.println("sss");
	}

	/*public OrderService(UserService userService){
		System.out.println("sss1");
	}*/
	/*public OrderService(UserService userService,String s){
		System.out.println("aaaa");
	}*/
	@Lazy
	@Autowired
	A y;
	//@Autowired
//	static X x;

	//	@Autowired
	A userService;

	// 在自动注入的情况下，约定set开头的方法，比如setAds(),Spring就会认为注入一个ads对象
	//@Autowired
	public void setUserService(A userService) {
		System.out.println("xxxxx");
		this.userService = userService;
	}

	/*	@Autowired
	public void setX(X x) {
		OrderService.x = x;
	}

	public void ox(){
		x.say();
	}*/
}
