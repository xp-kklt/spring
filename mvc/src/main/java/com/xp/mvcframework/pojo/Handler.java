package com.xp.mvcframework.pojo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author xupan
 * @date 2021/12/16 19:09
 **/
public class Handler {
	private Object controller;
	private Method method;
	private Pattern pattern;// spring中URL支持正则
	private Map<String, Integer> paramIndexMapping; // 参数顺序，是为了参数绑定，key:参数名，value:第几个参数

	public Handler(Object controller, Method method, Pattern pattern) {
		this.controller = controller;
		this.method = method;
		this.pattern = pattern;
		this.paramIndexMapping = new HashMap<>();
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public Map<String, Integer> getParamIndexMapping() {
		return paramIndexMapping;
	}

	public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
		this.paramIndexMapping = paramIndexMapping;
	}
}
