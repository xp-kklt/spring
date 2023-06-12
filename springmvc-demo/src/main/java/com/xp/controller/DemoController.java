package com.xp.controller;

import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @author xupan
 * @date 2021/12/13 17:27
 **/
@Controller
@RequestMapping("/demo")
public class DemoController {

	/**
	 * url: http://localhost:8080/demo/handle01
	 */
	@RequestMapping("/handle01")
	public ModelAndView handle01() {
		Date date = new Date();
		// 返回时间到前端页面
		// 封装了数据和页面信息的model
		ModelAndView modelAndView = new ModelAndView();
		// addObject其实是向请求域中request.setAttribute("date",date);
		modelAndView.addObject("date", date);
		// 视图信息(封装跳转的页面信息)
		modelAndView.setViewName("success");
		System.out.println("eee");
		return modelAndView;
	}

	/**
	 * springmvc在handler方法上传入Map,Model,ModelMap参数，并且向这些参数中保存数据(放入到请求域当中)，都可以在页面中获取到
	 * <p>
	 * 他们之间是什么关系呢
	 * 运行时候的具体类型都是BindingAwareModelMap，相当于给BindingAwareModelMap中保存的数据会被放在请求域中
	 * <p>
	 * Map(jdk接口）  Model(spring接口）
	 * ModelMap(class,实现map接口)
	 * <p>
	 * BindingAwareModelMap继承了ExtendedModelMap，ExtendedModelMap继承了ModelMap,实现了Model接口
	 */


	/*直接声明ModelMap*/
	@RequestMapping("/handle02")
	public String handle02(ModelMap modelMap) {
		Date date = new Date();
		modelMap.addAttribute("date", date);
		System.out.println("modelMap:" + modelMap);
		System.out.println("modelMap:" + modelMap.getClass());
		return "success";
	}

	/*直接声明model*/
	@RequestMapping("/handle03")
	public String handle03(Model model) {
		Date date = new Date();
		model.addAttribute("date", date);
		System.out.println("model:" + model);
		System.out.println("model:" + model.getClass());
		return "success";
	}


	/*直接声明形参map*/
	@RequestMapping("/handle04")
	public String handle04(Map<String, Object> map) {
		Date date = new Date();
		map.put("date", date);
		System.out.println("map:" + map);
		System.out.println("map:" + map.getClass());
		return "success";
	}
}
