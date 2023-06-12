package com.xp.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author xupan
 * @date 2021/12/19 21:21
 * <p>
 * 观察者
 **/
public class Master4JDK implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(o + "," + arg);
	}
}
