package com.xp.aop.aspectJ;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/07/03 20:32
 **/
@Component
@Aspect
public class NotVeryUseFullAspect {

	@Pointcut("within(com.xp.aop.service.OrderService)")
	private void pointCutWithin() {

	}


	@Before("pointCutWithin()")
	public void doAccessCheck() {
		System.out.println("aop-----------");
	}


}
