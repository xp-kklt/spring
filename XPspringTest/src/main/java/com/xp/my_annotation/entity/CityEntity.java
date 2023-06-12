package com.xp.my_annotation.entity;

import com.xp.my_annotation.annotation.XpEntity;

/**
 * @author xupan
 * @date 2021/08/15 20:16
 **/
@XpEntity("city")
public class CityEntity {

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CityEntity{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
