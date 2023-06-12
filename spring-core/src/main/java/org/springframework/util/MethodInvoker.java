/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.springframework.lang.Nullable;

/**
 * Helper class that allows for specifying a method to invoke in a declarative
 * fashion, be it static or non-static.
 *
 * <p>Usage: Specify "targetClass"/"targetMethod" or "targetObject"/"targetMethod",
 * optionally specify arguments, prepare the invoker. Afterwards, you may
 * invoke the method any number of times, obtaining the invocation result.
 *
 * @author Colin Sampaleanu
 * @author Juergen Hoeller
 * @see #prepare
 * @see #invoke
 * @since 19.02.2004
 */
public class MethodInvoker {

	private static final Object[] EMPTY_ARGUMENTS = new Object[0];


	@Nullable
	protected Class<?> targetClass;

	@Nullable
	private Object targetObject;

	@Nullable
	private String targetMethod;

	@Nullable
	private String staticMethod;

	@Nullable
	private Object[] arguments;

	/**
	 * The method we will call.
	 */
	@Nullable
	private Method methodObject;


	/**
	 * Set the target class on which to call the target method.
	 * Only necessary when the target method is static; else,
	 * a target object needs to be specified anyway.
	 *
	 * @see #setTargetObject
	 * @see #setTargetMethod
	 */
	public void setTargetClass(@Nullable Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	/**
	 * Return the target class on which to call the target method.
	 */
	@Nullable
	public Class<?> getTargetClass() {
		return this.targetClass;
	}

	/**
	 * Set the target object on which to call the target method.
	 * Only necessary when the target method is not static;
	 * else, a target class is sufficient.
	 *
	 * @see #setTargetClass
	 * @see #setTargetMethod
	 */
	public void setTargetObject(@Nullable Object targetObject) {
		this.targetObject = targetObject;
		if (targetObject != null) {
			this.targetClass = targetObject.getClass();
		}
	}

	/**
	 * Return the target object on which to call the target method.
	 */
	@Nullable
	public Object getTargetObject() {
		return this.targetObject;
	}

	/**
	 * Set the name of the method to be invoked.
	 * Refers to either a static method or a non-static method,
	 * depending on a target object being set.
	 *
	 * @see #setTargetClass
	 * @see #setTargetObject
	 */
	public void setTargetMethod(@Nullable String targetMethod) {
		this.targetMethod = targetMethod;
	}

	/**
	 * Return the name of the method to be invoked.
	 */
	@Nullable
	public String getTargetMethod() {
		return this.targetMethod;
	}

	/**
	 * Set a fully qualified static method name to invoke,
	 * e.g. "example.MyExampleClass.myExampleMethod".
	 * Convenient alternative to specifying targetClass and targetMethod.
	 *
	 * @see #setTargetClass
	 * @see #setTargetMethod
	 */
	public void setStaticMethod(String staticMethod) {
		this.staticMethod = staticMethod;
	}

	/**
	 * Set arguments for the method invocation. If this property is not set,
	 * or the Object array is of length 0, a method with no arguments is assumed.
	 */
	public void setArguments(Object... arguments) {
		this.arguments = arguments;
	}

	/**
	 * Return the arguments for the method invocation.
	 */
	public Object[] getArguments() {
		return (this.arguments != null ? this.arguments : EMPTY_ARGUMENTS);
	}


	/**
	 * Prepare the specified method.
	 * The method can be invoked any number of times afterwards.
	 *
	 * @see #getPreparedMethod
	 * @see #invoke
	 */
	public void prepare() throws ClassNotFoundException, NoSuchMethodException {
		if (this.staticMethod != null) {
			int lastDotIndex = this.staticMethod.lastIndexOf('.');
			if (lastDotIndex == -1 || lastDotIndex == this.staticMethod.length()) {
				throw new IllegalArgumentException(
						"staticMethod must be a fully qualified class plus method name: " +
								"e.g. 'example.MyExampleClass.myExampleMethod'");
			}
			String className = this.staticMethod.substring(0, lastDotIndex);
			String methodName = this.staticMethod.substring(lastDotIndex + 1);
			this.targetClass = resolveClassName(className);
			this.targetMethod = methodName;
		}

		Class<?> targetClass = getTargetClass();
		String targetMethod = getTargetMethod();
		Assert.notNull(targetClass, "Either 'targetClass' or 'targetObject' is required");
		Assert.notNull(targetMethod, "Property 'targetMethod' is required");

		Object[] arguments = getArguments();
		Class<?>[] argTypes = new Class<?>[arguments.length];
		for (int i = 0; i < arguments.length; ++i) {
			argTypes[i] = (arguments[i] != null ? arguments[i].getClass() : Object.class);
		}

		// Try to get the exact method first.
		try {
			this.methodObject = targetClass.getMethod(targetMethod, argTypes);
		} catch (NoSuchMethodException ex) {
			// Just rethrow exception if we can't get any match.
			this.methodObject = findMatchingMethod();
			if (this.methodObject == null) {
				throw ex;
			}
		}
	}

	/**
	 * Resolve the given class name into a Class.
	 * <p>The default implementations uses {@code ClassUtils.forName},
	 * using the thread context class loader.
	 *
	 * @param className the class name to resolve
	 * @return the resolved Class
	 * @throws ClassNotFoundException if the class name was invalid
	 */
	protected Class<?> resolveClassName(String className) throws ClassNotFoundException {
		return ClassUtils.forName(className, ClassUtils.getDefaultClassLoader());
	}

	/**
	 * Find a matching method with the specified name for the specified arguments.
	 *
	 * @return a matching method, or {@code null} if none
	 * @see #getTargetClass()
	 * @see #getTargetMethod()
	 * @see #getArguments()
	 */
	@Nullable
	protected Method findMatchingMethod() {
		String targetMethod = getTargetMethod();
		Object[] arguments = getArguments();
		int argCount = arguments.length;

		Class<?> targetClass = getTargetClass();
		Assert.state(targetClass != null, "No target class set");
		Method[] candidates = ReflectionUtils.getAllDeclaredMethods(targetClass);
		int minTypeDiffWeight = Integer.MAX_VALUE;
		Method matchingMethod = null;

		for (Method candidate : candidates) {
			if (candidate.getName().equals(targetMethod)) {
				if (candidate.getParameterCount() == argCount) {
					Class<?>[] paramTypes = candidate.getParameterTypes();
					int typeDiffWeight = getTypeDifferenceWeight(paramTypes, arguments);
					if (typeDiffWeight < minTypeDiffWeight) {
						minTypeDiffWeight = typeDiffWeight;
						matchingMethod = candidate;
					}
				}
			}
		}

		return matchingMethod;
	}

	/**
	 * Return the prepared Method object that will be invoked.
	 * <p>Can for example be used to determine the return type.
	 *
	 * @return the prepared Method object (never {@code null})
	 * @throws IllegalStateException if the invoker hasn't been prepared yet
	 * @see #prepare
	 * @see #invoke
	 */
	public Method getPreparedMethod() throws IllegalStateException {
		if (this.methodObject == null) {
			throw new IllegalStateException("prepare() must be called prior to invoke() on MethodInvoker");
		}
		return this.methodObject;
	}

	/**
	 * Return whether this invoker has been prepared already,
	 * i.e. whether it allows access to {@link #getPreparedMethod()} already.
	 */
	public boolean isPrepared() {
		return (this.methodObject != null);
	}

	/**
	 * Invoke the specified method.
	 * <p>The invoker needs to have been prepared before.
	 *
	 * @return the object (possibly null) returned by the method invocation,
	 * or {@code null} if the method has a void return type
	 * @throws InvocationTargetException if the target method threw an exception
	 * @throws IllegalAccessException    if the target method couldn't be accessed
	 * @see #prepare
	 */
	@Nullable
	public Object invoke() throws InvocationTargetException, IllegalAccessException {
		// In the static case, target will simply be {@code null}.
		Object targetObject = getTargetObject();
		Method preparedMethod = getPreparedMethod();
		if (targetObject == null && !Modifier.isStatic(preparedMethod.getModifiers())) {
			throw new IllegalArgumentException("Target method must not be non-static without a target");
		}
		ReflectionUtils.makeAccessible(preparedMethod);
		return preparedMethod.invoke(targetObject, getArguments());
	}


	/**
	 * 判断候选方法的声明参数类型与应该使用该方法调用的特定参数列表之间匹配的算法。
	 * 确定表示类型和参数之间的类层次结构差异的权重。
	 * 直接匹配，即类型 Integer -> 类 Integer 的 arg，不会增加结果 - 所有直接匹配都意味着权重 0。
	 * 类型 Object 和 Integer 类的 arg 之间的匹配会使权重增加 2，
	 * 因为在层次结构（即 Object）中上升的超类 2 是最后一个仍然匹配所需类型 Object 的。
	 * 类型 Number 和类 Integer 将相应地增加 1 的权重，
	 * 因为超类 1 在层次结构（即 Number）上升级仍然匹配所需的类型 Number。
	 * 因此，对于 Integer 类型的 arg，构造函数 (Integer) 将优先于构造函数 (Number)，
	 * 而构造函数 (Number) 又将优先于构造函数 (Object)。
	 * 所有参数权重都会累积。
	 * 这是 MethodInvoker 本身使用的算法，
	 * 也是 Spring 的 bean 容器中用于构造函数和工厂方法选择的算法
	 * （在宽松构造函数解析的情况下，这是常规 bean 定义的默认值）。
	 * <p>
	 * Algorithm that judges the match between the declared parameter types of a candidate method
	 * and a specific list of arguments that this method is supposed to be invoked with.
	 * <p>Determines a weight that represents the class hierarchy difference between types and
	 * arguments. A direct match, i.e. type Integer -> arg of class Integer, does not increase
	 * the result - all direct matches means weight 0. A match between type Object and arg of
	 * class Integer would increase the weight by 2, due to the superclass 2 steps up in the
	 * hierarchy (i.e. Object) being the last one that still matches the required type Object.
	 * Type Number and class Integer would increase the weight by 1 accordingly, due to the
	 * superclass 1 step up the hierarchy (i.e. Number) still matching the required type Number.
	 * Therefore, with an arg of type Integer, a constructor (Integer) would be preferred to a
	 * constructor (Number) which would in turn be preferred to a constructor (Object).
	 * All argument weights get accumulated.
	 * <p>Note: This is the algorithm used by MethodInvoker itself and also the algorithm
	 * used for constructor and factory method selection in Spring's bean container (in case
	 * of lenient constructor resolution which is the default for regular bean definitions).
	 *
	 * @param paramTypes the parameter types to match
	 * @param args       the arguments to match
	 * @return the accumulated weight for all arguments
	 */
	public static int getTypeDifferenceWeight(Class<?>[] paramTypes, Object[] args) {
		int result = 0;
		for (int i = 0; i < paramTypes.length; i++) {
			// 判断args[i]是不是paramTypes[i]的子类
			if (!ClassUtils.isAssignableValue(paramTypes[i], args[i])) {
				return Integer.MAX_VALUE;
			}
			// 有继承关系
			if (args[i] != null) {
				Class<?> paramType = paramTypes[i];
				Class<?> superClass = args[i].getClass().getSuperclass();
				// 这里就是看是不是父类：
				// 1.不是，权重不变
				// 2.每隔一层，权重加2
				while (superClass != null) {
					// 这里说明 是直接父类
					if (paramType.equals(superClass)) {
						// 权重加2，权重越大，越不好
						result = result + 2;
						superClass = null;
					}
					// 如果superClass是paramType的子类，这样子的话，paramType起码是args[i]的爷爷哈哈哈
					else if (ClassUtils.isAssignable(paramType, superClass)) {
						result = result + 2;
						superClass = superClass.getSuperclass();
					}
					//说明paramType是args[i]的接口，或者二者是同一个类
					else {
						superClass = null;
					}
				}
				// 如果是接口，权重加1
				if (paramType.isInterface()) {
					result = result + 1;
				}
			}
		}
		return result;
	}

}
