/*
 * Copyright 2002-2013 the original author or authors.
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

package org.springframework.context.annotation;

/**
 * 枚举各种作用域代理scoped-proxy的选项。
 * Enumerates the various scoped-proxy options.
 * <p>
 * 有关范围代理的更完整讨论，请参阅 Spring 参考文档中标题为“<em>Scoped beans as dependencies<em>”的部分
 * <p>For a more complete discussion of exactly what a scoped proxy is, see the
 * section of the Spring reference documentation entitled '<em>Scoped beans as
 * dependencies</em>'.
 *
 * @author Mark Fisher
 * @see ScopeMetadata
 * @since 2.5
 */
public enum ScopedProxyMode {

	/**
	 * 默认值通常等于 {@link #NO}，除非在组件扫描component-scan指令级别配置了不同的默认值。
	 * <p>
	 * Default typically equals {@link #NO}, unless a different default
	 * has been configured at the component-scan instruction level.
	 */
	DEFAULT,

	/**
	 * 不要创建范围代理。
	 * 当与非单例范围的实例一起使用时，此代理模式通常没有用，
	 * 如果要将其用作依赖项，则应支持使用 {@link #INTERFACES} 或 {@link #TARGET_CLASS} 代理模式。
	 * <p>
	 * Do not create a scoped proxy.
	 * <p>This proxy-mode is not typically useful when used with a
	 * non-singleton scoped instance, which should favor the use of the
	 * {@link #INTERFACES} or {@link #TARGET_CLASS} proxy-modes instead if it
	 * is to be used as a dependency.
	 */
	NO,

	/**
	 * 创建一个 JDK 动态代理，实现由目标对象类公开的所有的接口。
	 * <p>
	 * Create a JDK dynamic proxy implementing <i>all</i> interfaces exposed by
	 * the class of the target object.
	 */
	INTERFACES,

	/**
	 * 创建一个基于类的代理（使用 CGLIB）。
	 * <p>
	 * Create a class-based proxy (uses CGLIB).
	 */
	TARGET_CLASS;

}
