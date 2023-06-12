package com.xp.demo.controller;

import com.xp.demo.service.IDemoService;
import com.xp.mvcframework.annotations.XpAutowired;
import com.xp.mvcframework.annotations.XpController;
import com.xp.mvcframework.annotations.XpRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xupan
 * @date 2021/12/16 15:33
 **/
@XpController
@XpRequestMapping("/demo")
public class DemoController {

	@XpAutowired
	private IDemoService demoService;

	@XpRequestMapping("/query")
	public String query(HttpServletRequest request, HttpServletResponse response, String name, String h) {
		return demoService.get(name);
	}
}
