package com.xp.observer;

/**
 * @author xupan
 * @date 2021/12/19 21:36
 **/
public class Test {
	public static void main(String[] args) {
		Subject subject = new Subject();
		Master4JDK master4JDK = new Master4JDK();
		subject.addObserver(master4JDK);
		//	subject.notifyObservers();
		subject.move(2);
		subject.move(1);
	}
}
