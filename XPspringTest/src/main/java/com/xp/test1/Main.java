package com.xp.test1;

import com.xp.test1.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		//ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();
		//	ac.scan("");

		/*UserDao dao = ac.getBean(UserDao.class);
		System.out.println(dao);
		String s = dao.getUser();
		System.out.println("xxxxxxxxxx");
		System.out.println(s);*/
		//	ClassPathXmlApplicationContext cpl = new ClassPathXmlApplicationContext("classpath:spring.xml");
		//	System.out.println(cpl.getBean(A.class).getB());
		//	System.out.println(cpl.getBean(UserDao.class).getaaa());
		//	A a = cpl.getBean(A.class);

		//	a.getBb();

		//method(null);
		//	System.out.println(cpl.getBean(A.class));
		//	System.out.println(cpl.getBean(A.class));
		//	System.out.println(cpl.getBean(A.class));

		//	System.out.println(cpl.getBean(C.class).getD());
		//	System.out.println(cpl.getBean(C.class).getD());
		//	System.out.println(cpl.getBean(C.class).getD());

		//	cpl.getBean(E.class).afterInit();
	/*	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig1.class);
*/
		//	ac.addBeanFactoryPostProcessor(new XpBeanDefinitionRegistryPostProcessor());
	/*	ac.refresh();

		System.out.println(ac.getBean(AppConfig1.class));
		System.out.println(ac.getBean(com.xp.app.E.class));
		System.out.println(ac.getBean(E.class));*/
//		ac.scan("com.xp");
		//	ac.close();
		//ac.stop();
		//ac.getBean(XpService.class).getService();
		//	System.out.println(ac.getBeanDefinition("bb"));
		//	System.out.println(ac.getBeanDefinition("appConfig").getClass().getSimpleName());
		//	System.out.println(ac.getBeanDefinition("xpService").getClass().getSimpleName());

		/*ClassPathXmlApplicationContext cl = new ClassPathXmlApplicationContext("classpath:spring.xml");
		System.out.println(cl.getBeanFactory().getBeanDefinition("bb"));
		System.out.println(cl.getBeanFactory().getBeanDefinition("bb").getPropertyValues().get("age"));
		System.out.println(cl.getBeanFactory().getBeanDefinition("bb").getClass().getSimpleName());
       *///所有在xml中定义的bean的beanDefinition都是GenericBeanDefinition
	}

	public static void method(Object o) {
		System.out.println("Object method");
	}

	public static void method(String s) {
		System.out.println("String method");
	}
}
