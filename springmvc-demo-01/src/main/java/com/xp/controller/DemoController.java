package com.xp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xupan
 * @date 2021/12/17 01:38
 **/
@Controller
public class DemoController {

	@RequestMapping("/index01")
	public String index01() {
		System.out.println("111****************");
		return "index";
	}
}
