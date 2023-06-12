package com.xp.lifecycle.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xupan
 * @date 2021/08/13 16:20
 **/
public class D {

	public D() {
		System.out.println("D");
	}

	@Configuration
	class bConfig {
		@Bean
		H h() {
			return new H();
		}
	}
}
