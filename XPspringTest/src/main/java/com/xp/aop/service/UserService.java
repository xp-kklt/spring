package com.xp.aop.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author xupan
 * @date 2021/07/03 20:41
 **/
@Component
public class UserService implements UserServiceInterface {

	public UserService() {
		System.out.println("start userService");
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
	}

	@Override
	public void testAop() {
		System.out.println("testAop-----");
	}
}
