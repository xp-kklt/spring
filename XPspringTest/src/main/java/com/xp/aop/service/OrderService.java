package com.xp.aop.service;


import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/07/03 20:39
 **/
@Component
//@Scope("prototype")
public class OrderService implements OrderServiceInterace {

	/**
	 * 下面两个构造方法，执行的是无参构造方法，因为这里是手动装配，有UserService的参数的构造函数并不会执行
	 * 如果在后置处理器中改一下，将装配方式改为构造函数装配，那么就会Spring执行有参的构造函数
	 * 如果是AUTOWIRE_NO，AUTOWIRE_BY_NAME，AUTOWIRE_BY_TYPE，还是执行的无参数默认构造方法
	 * <p>
	 * 这里也可以证明加了@Component的类并不是自动装配
	 * <p>
	 * <p>
	 * 因为是手动装配，而且装配方式也不是AUTOWIRED_CONSTRUCTOR,所以：
	 * 1.如果除了默认的无参数构造方法，还有一个或者多个构造方法，那么推断出来的为null,  (那么直接不推断了,因为推断出来多个没什么用处)
	 * 2.如果只有一个默认的无参数构造方法，那也没必要推断了,那么推断出来的为null
	 * 3.如果只有一个有参数构造函数，那推断出来就是它,只有一个
	 * 4.如果有两个有参构造方法，那么报错，
	 * 5.如果有多个有参构造函数，推断出来还是null
	 * 6.如果加了@Autowired，
	 * <p>
	 * 如果装配方式是AUTOWIRED_CONSTRUCTOR，那就是自动装配了。
	 * 首先会选最长的。
	 * 如果最长的一样长，就会选择排在前面的。
	 */

	/*//@Autowired(required = true)
	public OrderService(){
		System.out.println("orderService no args");
	}
*/
	//@Autowired(required = false)
	public OrderService(LoginService loginService1) {
		System.out.println("loginService constructor");
	}

	// 手动装配
	//@Autowired(required = true)
	public OrderService(UserService userService1) {
		System.out.println("userService constructor");
	}
	/*public OrderService(UserServiceInterface userService){
		System.out.println("userService constructor");
	}
*/
//	public OrderService(Class userService){
//		System.out.println("class constructor");
//	}


	/*public OrderService(UserService userService,LoginService loginService){
		System.out.println("userService loginService constructor");
	}
*/
	/*public OrderService(UserServiceInterface userService,LoginService loginService){
		System.out.println("userService string constructor");
	}
*/


	@Override
	public void testAop() {
		System.out.println("order--aop---logic-----");
	}

	@Override
	public String toString() {
		return "its me";
	}
}
