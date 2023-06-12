package com.xp.lifecycle.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xupan
 * @date 2021/08/11 10:00
 **/
@Component
public class B implements InitializingBean, DisposableBean {

	@Configuration
	class bConfig {
		@Bean
		G g() {
			return new G();
		}
	}

	public B() {
		System.out.println("B");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("init2");
	}

	@PostConstruct
	public void init1() {
		System.out.println("init1");
	}

	public void init2() {
		System.out.println("init3");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy2");
	}

	@PreDestroy
	public void destroy1() {
		System.out.println("destroy1");
	}

	public void destroy2() {
		System.out.println("destroy3");
	}
}
