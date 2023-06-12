package com.xp.my_annotation.utils;

import com.xp.my_annotation.annotation.XpEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xupan
 * @date 2021/08/15 20:22
 **/
public class CommUtil {

	/**
	 * 通过一个对象构建一条查询的sql
	 *
	 * @param object
	 * @return
	 */
	public static String buildQuerySqlForEntity(Object object) throws IllegalAccessException, ClassNotFoundException {
		Class clazz = object.getClass();
		String sql = "select * from ";
		if (clazz.isAnnotationPresent(XpEntity.class)) {
			XpEntity annotation = (XpEntity) clazz.getDeclaredAnnotation(XpEntity.class);
			String table = annotation.value();
			sql += table;
			//Method[] methods = object.getClass().getDeclaredMethods();
			Field[] declaredFields = object.getClass().getDeclaredFields();
			if (declaredFields.length > 0) {
				sql += " where ";
				for (Field declaredField : declaredFields) {
					declaredField.setAccessible(true);
					String name = declaredField.getName();
					String value = (String) declaredField.get(object);
					sql += "'" + name + "'" + "=" + "\"" + value + "\"" + " and ";
				}
			}
			sql = sql.substring(0, sql.length() - 4);
			System.out.println("***************");
			String name = clazz.getName();
			Class<?> aClass = Class.forName(name);
			System.out.println(aClass.toString());
			Field[] declaredFields1 = aClass.getDeclaredFields();
			for (Field declaredField : declaredFields1) {
				declaredField.setAccessible(true);
				String name1 = declaredField.getName();
				String value1 = (String) declaredField.get(object);
				System.out.println(name1);
				System.out.println(value1);
			}
			System.out.println(name);
		}

		return sql;
	}
}
