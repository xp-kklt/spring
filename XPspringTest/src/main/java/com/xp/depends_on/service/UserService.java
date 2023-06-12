package com.xp.depends_on.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xupan
 * @date 2021/08/06 11:50
 **/
@Component
public class UserService {

	@PostConstruct
	public void init() {
		System.out.println("userService init");
	}


	@PreDestroy
	private void destroy() {
		System.out.println("userService destroy");
	}
}
