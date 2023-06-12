package com.xp.handler;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 *
 * @author xupan
 * @date 2021/12/15 18:03
 **/
public class MyHandler implements HandlerInterceptor {

	/**
	 * handler业务执行之前执行该方法，返回true的话，放行，返回false的话中止拦截
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("my intercepter pre handle");
		return true;
	}

	/**
	 * handler业务执行之后还没有返回的时候执行该方法
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("my intercepter post handle");
	}

	/**
	 * 页面跳转渲染完毕以后执行该方法
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("my intercepter afterCompletion");
	}
}
