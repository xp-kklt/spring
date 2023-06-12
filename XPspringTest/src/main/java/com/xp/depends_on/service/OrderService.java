package com.xp.depends_on.service;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xupan
 * @date 2021/08/06 11:48
 **/
@Component
@DependsOn("userService")
public class OrderService {

	@PostConstruct
	private void init() {
		System.out.println("orderService init");
	}

	@PreDestroy
	private void destroy() {
		System.out.println("orderService destroy");
	}
}
