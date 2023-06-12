package com.xp.thirdProcessor.service;

/**
 * @author xupan
 * @date 2021/07/22 17:04
 **/
public class ChildBean {
	String name;
	Integer age;

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ChildBean{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
