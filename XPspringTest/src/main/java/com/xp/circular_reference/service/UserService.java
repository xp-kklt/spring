package com.xp.circular_reference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/06/24 17:00
 **/
@Component
public class UserService {

	@Autowired
	OrderService orderService;

	public UserService() {
		System.out.println("userService start");
	}

	public void sing() {
		System.out.println("im user");
	}
}
