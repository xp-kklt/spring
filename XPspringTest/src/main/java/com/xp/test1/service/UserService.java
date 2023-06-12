package com.xp.test1.service;

import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/03/10 23:42
 **/
@Component
public class UserService {

	public UserService() {
		System.out.println("construct from UserService");
	}

	public void close() {
		System.out.println("close");
	}
}
