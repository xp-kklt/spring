package com.xp.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @author xupan
 * @date 2021/12/19 21:28
 * <p>
 * 被观察的对象
 **/
public class Subject extends Observable {

	public void move(Integer i) {
		setChanged();
		if (i == 1) {
			notifyObservers("a");
		} else if (i == 2) {
			notifyObservers("b");
		} else {
			notifyObservers();
		}
	}

	/*List<Observer> observerList = new ArrayList<>();
	// 添加观察者
	@Override
	public synchronized void addObserver(Observer o) {
		observerList.add(o);
	}

	// 删除观察者
	@Override
	public synchronized void deleteObserver(Observer o) {
		observerList.remove(o);
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	@Override
	public void notifyObservers(Object arg) {
		for (Observer observer : observerList) {
			observer.update(this,arg);
		}
	}*/
}
