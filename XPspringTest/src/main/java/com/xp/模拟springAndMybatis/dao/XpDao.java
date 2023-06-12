package com.xp.模拟springAndMybatis.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


public interface XpDao {

	@Select("select id from user")
	public void say();

	@Select("select name,age from user")
	public void sing();
}
