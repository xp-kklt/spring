package com.xp.lifecycle.config;

import com.xp.lifecycle.post_processor.XpBeanFactoryPostProcessor;
import com.xp.lifecycle.service.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author xupan
 * @date 2021/06/24 17:15
 **/
@Configuration("app")
//@ComponentScan("com.xp.lifecycle.service")
@ComponentScan(basePackages = {"com.xp.lifecycle.config"}, basePackageClasses = {OrderService.class},
		includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {OrderService.class})},
		excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = A.class)})
@Import(XpBeanFactoryPostProcessor.class)
public class AppConfig {

	/*@Bean(initMethod = "initaaa",destroyMethod = "destroyA")
	public A a(){
		return new A();
	}

	@Bean(initMethod = "init2",destroyMethod = "destroy2")
	public B b(){
		return new B();
	}*/
	@Bean
	public D d() {
		return new D();
	}

	/*@Bean
	public F f(){
		return new F();
	}*/
	@Configuration()
//	@Documented
//	@ComponentScan("com.xp.lifecycle.service.impl")
	class config {
		/*@Bean
		public D dd(){
			return new D();
		}*/
		@Bean(name = {"aa", "ff"})
		@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
		//@AliasFor("aa")
		public F f() {
			return new F();
		}
	}
	/*public @interface aa{}*/
}
