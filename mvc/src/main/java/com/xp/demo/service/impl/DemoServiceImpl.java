package com.xp.demo.service.impl;

import com.xp.demo.service.IDemoService;
import com.xp.mvcframework.annotations.XpService;

/**
 * @author xupan
 * @date 2021/12/16 15:36
 **/
@XpService
public class DemoServiceImpl implements IDemoService {
	@Override
	public String get(String name) {
		System.out.println("service实现类中的name参数：" + name);
		return name;
	}
}
