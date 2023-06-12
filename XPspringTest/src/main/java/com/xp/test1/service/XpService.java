package com.xp.test1.service;

import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/03/10 23:42
 **/
@Component
public class XpService {
	public void setAge(int age) {
		this.age = age;
	}

	private int age;

	//@Autowired
	UserService userService;

	public XpService() {
		System.out.println("construct from XpService");
	}

	public void setUserService111(UserService userService) {
		this.userService = userService;
	}

	public void getService() {
		System.out.println(userService);
	}
}
