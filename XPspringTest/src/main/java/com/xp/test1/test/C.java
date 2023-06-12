package com.xp.test1.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/03/10 21:30
 **/
//@Component
/*public abstract class C {

//	@Autowired
//	ApplicationContext applicationContext;

	@Autowired
	D d;

*//*	public void getD(){
		System.out.println(applicationContext.getBean(D.class));
	}*//*

	@Lookup
	public abstract D getD();
}*/
@Component
public class C {

	@Autowired
	D d;

/*	public void getD(){
		System.out.println(applicationContext.getBean(D.class));
	}*/

	@Lookup
	public D getD() {
		return null;
	}
}
