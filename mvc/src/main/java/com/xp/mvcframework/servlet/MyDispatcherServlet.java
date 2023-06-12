package com.xp.mvcframework.servlet;

import com.xp.mvcframework.annotations.XpAutowired;
import com.xp.mvcframework.annotations.XpController;
import com.xp.mvcframework.annotations.XpRequestMapping;
import com.xp.mvcframework.annotations.XpService;
import com.xp.mvcframework.pojo.Handler;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author xupan
 * @date 2021/12/15 23:59
 **/
public class MyDispatcherServlet extends HttpServlet {

	private Properties properties = new Properties();
	// 缓存扫描到的类的全限定名
	private List<String> classNames = new ArrayList<>();
	// IOC容器
	private Map<String, Object> iocMap = new HashMap<>();
	// url和method建立映射关系
	private Map<String, Handler> mappingMap = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// 1.加载配置文件 springmvc.properties
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		doLoadConfig(contextConfigLocation);

		// 2.扫描相关的类，扫描注解
		doScan(properties.getProperty("scanPackage"));

		// 3.初始化bean对象（实现IOC容器，基于注解）
		doInstance();

		// 4.实现依赖注入
		doAutowired();

		// 5.构造一个HandlerMapping处理器映射器，将配置好的url和method建立映射关系
		initHandlerMapping();
		System.out.println("初始化完成");
		// 6.等待请求进入，处理请求
	}

	// 构造一个处理器映射器
	private void initHandlerMapping() {
		if (iocMap.isEmpty()) {
			return;
		}
		Set<Map.Entry<String, Object>> entries = iocMap.entrySet();
		for (Map.Entry<String, Object> entry : entries) {
			Object object = entry.getValue();
			Class clazz = object.getClass();
			if (clazz.isAnnotationPresent(XpController.class) && clazz.isAnnotationPresent(XpRequestMapping.class)) {
				XpRequestMapping annotation = (XpRequestMapping) clazz.getAnnotation(XpRequestMapping.class);
				String url = "";
				if (null != annotation) {
					url = annotation.value();
				}
				Method[] methods = clazz.getMethods();
				for (Method method : methods) {
					XpRequestMapping methodAnnotation = method.getAnnotation(XpRequestMapping.class);
					if (null == methodAnnotation) {
						continue;
					}
					String methodUrl = methodAnnotation.value();
					url = url + methodUrl;

					// 把method所有信息及其url封装成一个handler
					Handler handler = new Handler(object, method, Pattern.compile(url));
					// 计算方法的参数位置信息
					Parameter[] parameters = method.getParameters();
					for (int i = 0; i < parameters.length; i++) {
						Parameter parameter = parameters[i];
						if (parameter.getType() == HttpServletRequest.class
								|| parameter.getType() == HttpServletResponse.class) {
							// 如果是request和response对象，那么参数名称写HttpServletRequest，HttpServletResponse
							handler.getParamIndexMapping().put(parameter.getType().getSimpleName(), i);
						} else {
							// 假设这里就是string
							handler.getParamIndexMapping().put(parameter.getName(), i);
						}
					}
					mappingMap.put(url, handler);
				}
			}
		}
	}

	// 依赖注入
	private void doAutowired() {
		if (iocMap.isEmpty()) {
			return;
		}
		Set<Map.Entry<String, Object>> entries = iocMap.entrySet();
		for (Map.Entry<String, Object> entry : entries) {
			Object object = entry.getValue();
			Class clazz = object.getClass();
			if (clazz.isAnnotationPresent(XpController.class)) {
				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field declaredField : declaredFields) {
					XpAutowired annotation = declaredField.getAnnotation(XpAutowired.class);
					if (null == annotation) {
						continue;
					}
					String beanName = annotation.value();
					if (StringUtils.isBlank(beanName)) {
						beanName = declaredField.getType().getSimpleName();
					}
					declaredField.setAccessible(true);
					try {
						declaredField.set(entry.getValue(), iocMap.get(beanName));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}

				}
			}

		}
	}

	// IOC容器
	// 基于classNames缓存的类的全限定名，以及反射技术，完成对象创建和管理
	private void doInstance() {
		if (classNames.size() == 0) {
			return;
		}

		for (int i = 0; i < classNames.size(); i++) {
			String className = classNames.get(i); // com.xp.demo.controller.DemoController
			// 反射创建对象
			//	Class clazz = className.getClass();
			Class<?> clazz = null;
			try {
				clazz = Class.forName(className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			// 区分Controller,Service
			if (clazz.isAnnotationPresent(XpController.class)) {
				// controller的id此处不做过多处理，不取value值了，就拿类的首字母小写作为id,保存到ioc容器中
				String simpleName = clazz.getSimpleName();// DemoController
				simpleName = lowerFirst(simpleName);
				Object o = null;
				try {
					o = clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				iocMap.put(simpleName, o);
			} else if (clazz.isAnnotationPresent(XpService.class)) {
				XpService annotation = clazz.getAnnotation(XpService.class);
				String simpleName = annotation.value();
				if (StringUtils.isNotBlank(simpleName)) {
					try {
						iocMap.put(simpleName, clazz.newInstance());
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				} else {
					simpleName = clazz.getSimpleName();
					simpleName = lowerFirst(simpleName);
					Object o = null;
					try {
						o = clazz.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					iocMap.put(simpleName, o);
				}

				//service层一般都有接口，面向接口开发，此时再以接口名为id,放一份到ioc容器中，便于后期根据借口类型注入
				Class<?>[] interfaces = clazz.getInterfaces();
				String interfaceName = interfaces[0].getSimpleName();
				try {
					iocMap.put(interfaceName, clazz.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			} else {
				continue;
			}
		}
	}

	private String lowerFirst(String str) {
		char[] chars = str.toCharArray();
		if ('A' <= chars[0] && chars[0] <= 'Z') {
			chars[0] += 32;
		}
		return String.valueOf(chars);

	}

	// 扫描类 (com.xp.demo)
	private void doScan(String scanPackage) {

		// 磁盘上的路径
		String scanPackagePath = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath() + scanPackage.replaceAll("\\.", "/");
		File pack = new File(scanPackagePath);
		File[] files = pack.listFiles();
		for (File file : files) {
			// 如果是子包
			if (file.isDirectory()) {
				// 递归
				doScan(scanPackage + "." + file.getName());
			} else if (file.getName().endsWith(".class")) {
				String className = scanPackage + "." + file.getName().replaceAll(".class", "");
				classNames.add(className);
			}
		}
		String packageSearchPath = "classpath*:" +
				scanPackage + '/' + "**/*.class";
	}

	public static void main(String[] args) {
		char[] chars = "com.xp.demo".toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '.') {
				chars[i] = '/';
			}
		}

		System.out.println(String.valueOf(chars));
		String scanPackage = String.valueOf(chars);
		String packageSearchPath = "classpath*:" +
				scanPackage + '/' + "**/*.class";
		String s = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath() + "com.xp.demo".replaceAll("\\.", "/");
		System.out.println(s);

	}

	// 加载配置文件
	private void doLoadConfig(String contextConfigLocation) {
		InputStream resourceAsStream =
				this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 处理请求，根据url来获取到Method,并且执行
		String requestURI = req.getRequestURI();
		System.out.println(requestURI);
		Handler handler = mappingMap.get(requestURI);
		if (null == handler) {
			resp.getWriter().write("404 not found");
		}
		Map<String, Integer> paramIndexMapping = handler.getParamIndexMapping();
		Object controller = handler.getController();
		Method method = handler.getMethod();
		Pattern pattern = handler.getPattern();

		// 参数绑定
		// 获取所有参数类型数组，这个数组的长度就是我们最后要传入的args数组的长度
		Class<?>[] parameterTypes = method.getParameterTypes();

		// 根据上述数组创建一个新的数组（参数数组，是要传入反射调用的）
		Object[] paramValues = new Object[parameterTypes.length];

		// 以下就是为了向参数数组中塞值，而且还得保证参数的顺序和方法中形参顺序一致
		Map<String, String[]> parameterMap = req.getParameterMap();

		// 遍历request中所有参数，（填充除request和response外的所有参数）
		for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
			// 如同 name=1&name=2    name=[1,2]
			String value = StringUtils.join(param.getValue(), ","); // 如同 1,2
			// 如果参数和方法中的参数匹配上了，填充数据
			if (!paramIndexMapping.containsKey(param.getKey())) {
				continue;
			}
			// 方法形参确实有该参数，找到他的索引位置，对应的把参数放入paramValues
			Integer index = paramIndexMapping.get(param.getKey());

			paramValues[index] = value;
		}
		Integer requestIndex = paramIndexMapping.get(HttpServletRequest.class.getSimpleName());
		paramValues[requestIndex] = req;
		Integer responseIndex = paramIndexMapping.get(HttpServletResponse.class.getSimpleName());
		paramValues[responseIndex] = resp;
		try {
			method.invoke(controller, paramValues);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
