package com.xp.thirdProcessor.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author xupan
 * @date 2021/07/26 15:50
 **/
@Component
public class OrderService implements InitializingBean, DisposableBean, Lifecycle, SmartLifecycle {
	@Autowired
	UserService userService;

	LoginService loginService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * @PostConstruct先执行， afterPropertiesSet()后执行
	 */
	@PostConstruct
	public void init111() {
		System.out.println("我是初始化");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("我是属性注入后的生命周期回调");
	}


	@PreDestroy
	public void destroy1() {
		System.out.println("我要销毁了");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("我是销毁");
	}

	private boolean isStart;

	@Override
	public void start() {
		System.out.println("我是容器，我启动了");
		isStart = true;
	}

	@Override
	public void stop() {
		System.out.println("我是容器，我停止了");
		isStart = false;
	}

	@Override
	public boolean isRunning() {
		return isStart;
	}

	// 这个方法返回true的话，就可以调用程序员自己重写的start()方法,也不用手动调用ac.start()
	// 返回false的话，容器自己调用自己的start()方法了
	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		System.out.println("我是 SmartLifecycle 的stop");
		callback.run();
	}

	// 这是优先级，越小（包括负数）越先启动，越后关闭
	@Override
	public int getPhase() {
		return 0;
	}

}
