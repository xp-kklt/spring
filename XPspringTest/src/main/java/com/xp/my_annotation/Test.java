package com.xp.my_annotation;

import com.xp.my_annotation.entity.CityEntity;
import com.xp.my_annotation.utils.CommUtil;

/**
 * @author xupan
 * @date 2021/08/15 20:25
 **/
public class Test {

	public static void main(String[] args) {
		CityEntity cityEntity = new CityEntity();
		cityEntity.setId("1");
		cityEntity.setName("杭州");
		String sql = null;
		try {
			sql = CommUtil.buildQuerySqlForEntity(cityEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(sql);
	}

}
