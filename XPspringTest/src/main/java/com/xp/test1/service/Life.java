package com.xp.test1.service;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/03/11 19:45
 **/
@Component
public class Life implements SmartLifecycle {

	private boolean flag;

	@Override
	public void start() {
		System.out.println("start");
		flag = true;
	}

	@Override
	public void stop() {
		System.out.println("stop");
		flag = false;
	}

	@Override
	public boolean isRunning() {
		return flag;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		System.out.println("stop");
		callback.run();
	}

	@Override
	public int getPhase() {
		return 0;
	}
}
