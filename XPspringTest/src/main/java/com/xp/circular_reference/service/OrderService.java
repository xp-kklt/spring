package com.xp.circular_reference.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/06/24 17:00
 **/
@Component
public class OrderService {

	@Autowired
	UserService userService;

	public OrderService() {
		System.out.println("orderService start");
	}

	public void say() {
		System.out.println("im order");
	}
}
