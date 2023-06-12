package com.xp.aop.config;

import com.xp.aop.post_processor.ConstructorBeanFactoryPostProcessor;
import com.xp.aop.post_processor.CustomAopBeanPostProcessor;
import com.xp.aop.service.A;
import com.xp.aop.service.B;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.*;

/**
 * @author xupan
 * @date 2021/06/24 17:15
 **/
@Configuration
@ComponentScan("com.xp.aop")
@EnableAspectJAutoProxy
//@Import(CustomAopBeanPostProcessor.class)
@Import(ConstructorBeanFactoryPostProcessor.class)
public class AppConfig {

	@Bean
	@Scope("prototype")
	public A a() {
		b();
		return new A();

	}

	@Bean
	@Scope("prototype")
	public B b() {
		//b();
		return new B();

	}


}
