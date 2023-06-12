/*
 * Copyright 2002-2016 the original author or authors.
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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 提供配置组件扫描指令与 {@Configuration} 类使用。
 * 并行提供与Spring XML的<context:component-scan>}支撑。
 * <p>
 * Configures component scanning directives for use with @{@link Configuration} classes.
 * Provides support parallel with Spring XML's {@code <context:component-scan>} element.
 * <p>
 * 可以指定 {@link #basePackageClasses} 或 {@link #basePackages}（或其别名 {@link #value}）
 * 来定义要扫描的特定包。
 * 如果没有定义特定的包，将从声明该注解的类的包开始扫描。
 *
 * <p>Either {@link #basePackageClasses} or {@link #basePackages} (or its alias
 * {@link #value}) may be specified to define specific packages to scan. If specific
 * packages are not defined, scanning will occur from the package of the
 * class that declares this annotation.
 * <p>
 * 注意 {@code <context:component-scan>} 元素有一个 {@code annotation-config} 属性；
 * 然而，这个注解没有。
 * 这是因为在几乎所有使用 {@code @ComponentScan} 的情况下，
 * 默认注解配置处理（例如处理 {@code @Autowired} 和朋友）已经被假定了。
 * 此外，当使用 {@link AnnotationConfigApplicationContext} 时，
 * 注解配置处理器总是被注册，这意味着在 {@code @ComponentScan} 级别禁用它们的任何尝试都将被忽略。
 *
 * <p>Note that the {@code <context:component-scan>} element has an
 * {@code annotation-config} attribute; however, this annotation does not. This is because
 * in almost all cases when using {@code @ComponentScan}, default annotation config
 * processing (e.g. processing {@code @Autowired} and friends) is assumed. Furthermore,
 * when using {@link AnnotationConfigApplicationContext}, annotation config processors are
 * always registered, meaning that any attempt to disable them at the
 * {@code @ComponentScan} level would be ignored.
 *
 * <p>See {@link Configuration @Configuration}'s Javadoc for usage examples.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @see Configuration
 * @since 3.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(ComponentScans.class)
public @interface ComponentScan {

	/**
	 * basePackages的别名
	 * 如果不需要其他属性，则允许更简洁的注释声明 -
	 * 例如，{@code @ComponentScan("org.my.pkg")} 而不是 {@code @ComponentScan(basePackages = "org.my.pkg")} .
	 * <p>
	 * Alias for {@link #basePackages}.
	 * <p>Allows for more concise annotation declarations if no other attributes
	 * are needed &mdash; for example, {@code @ComponentScan("org.my.pkg")}
	 * instead of {@code @ComponentScan(basePackages = "org.my.pkg")}.
	 */
	@AliasFor("basePackages")
	String[] value() default {};

	/**
	 * {@link #value} 是此属性的别名（并与之互斥）。
	 * <p>
	 * Base packages to scan for annotated components.
	 * <p>{@link #value} is an alias for (and mutually exclusive with) this
	 * attribute.
	 * <p>Use {@link #basePackageClasses} for a type-safe alternative to
	 * String-based package names.
	 */
	@AliasFor("value")
	String[] basePackages() default {};

	/**
	 * 用来指定某个类所在包下的所有组件
	 * <p>
	 * {@link #basePackages} 的类型安全替代方案，用于指定要扫描带注解组件的包。
	 * 将扫描指定的每个类的包。
	 * 考虑在每个包中创建一个特殊的无操作标记类或接口，除了被此属性引用外，没有其他用途。
	 * <p>
	 * Type-safe alternative to {@link #basePackages} for specifying the packages
	 * to scan for annotated components. The package of each class specified will be scanned.
	 * <p>Consider creating a special no-op marker class or interface in each package
	 * that serves no purpose other than being referenced by this attribute.
	 */
	Class<?>[] basePackageClasses() default {};

	/**
	 * {@link BeanNameGenerator} 类用于命名 Spring 容器内检测到的组件。
	 * {@link BeanNameGenerator} 接口本身的默认值表明用于处理此 {@code @ComponentScan}
	 * 的扫描器应使用其继承的 bean 名称生成器，
	 * 例如默认 {@link AnnotationBeanNameGenerator} 或在引导时提供给应用程序上下文的任何自定义实例。
	 * <p>
	 * The {@link BeanNameGenerator} class to be used for naming detected components
	 * within the Spring container.
	 * <p>The default value of the {@link BeanNameGenerator} interface itself indicates
	 * that the scanner used to process this {@code @ComponentScan} annotation should
	 * use its inherited bean name generator, e.g. the default
	 * {@link AnnotationBeanNameGenerator} or any custom instance supplied to the
	 * application context at bootstrap time.
	 *
	 * @see AnnotationConfigApplicationContext#setBeanNameGenerator(BeanNameGenerator)
	 */
	Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

	/**
	 * {@link ScopeMetadataResolver} 用于解析检测到的bean的scope。
	 * <p>
	 * The {@link ScopeMetadataResolver} to be used for resolving the scope of detected components.
	 */
	Class<? extends ScopeMetadataResolver> scopeResolver() default AnnotationScopeMetadataResolver.class;

	/**
	 * 指示是否应该为检测到的bean生成代理，这在以代理风格的方式使用范围时可能是必要的。
	 * 默认值遵循用于执行实际扫描的组件扫描器的默认行为。
	 * 请注意，设置此属性会覆盖为 {@link #scopeResolver} 设置的任何值。
	 * <p>
	 * Indicates whether proxies should be generated for detected components, which may be
	 * necessary when using scopes in a proxy-style fashion.
	 * <p>The default is defer to the default behavior of the component scanner used to
	 * execute the actual scan.
	 * <p>Note that setting this attribute overrides any value set for {@link #scopeResolver}.
	 *
	 * @see ClassPathBeanDefinitionScanner#setScopedProxyMode(ScopedProxyMode)
	 */
	ScopedProxyMode scopedProxy() default ScopedProxyMode.DEFAULT;

	/**
	 * 控制符合组件检测条件的类文件。
	 * 默认：
	 * <考虑使用 {@link #includeFilters} 和 {@link #excludeFilters} 以获得更灵活的方法。
	 * <p>
	 * Controls the class files eligible for component detection.
	 * <p>Consider use of {@link #includeFilters} and {@link #excludeFilters}
	 * for a more flexible approach.
	 */
	String resourcePattern() default ClassPathScanningCandidateComponentProvider.DEFAULT_RESOURCE_PATTERN;

	/**
	 * Indicates whether automatic detection of classes annotated with {@code @Component}
	 * {@code @Repository}, {@code @Service}, or {@code @Controller} should be enabled.
	 */
	boolean useDefaultFilters() default true;

	/**
	 * 指定哪些类型有资格进行组件扫描。
	 * 进一步将候选组件集从 {@link #basePackages} 中的所有内容缩小到与给定的一个或多个过滤器匹配的基本包中的所有内容。
	 * <p>
	 * Specifies which types are eligible for component scanning.
	 * <p>Further narrows the set of candidate components from everything in {@link #basePackages}
	 * to everything in the base packages that matches the given filter or filters.
	 * <p>Note that these filters will be applied in addition to the default filters, if specified.
	 * Any type under the specified base packages which matches a given filter will be included,
	 * even if it does not match the default filters (i.e. is not annotated with {@code @Component}).
	 *
	 * @see #resourcePattern()
	 * @see #useDefaultFilters()
	 */
	Filter[] includeFilters() default {};

	/**
	 * 指定哪些类型不符合组件扫描条件。
	 * <p>
	 * Specifies which types are not eligible for component scanning.
	 *
	 * @see #resourcePattern
	 */
	Filter[] excludeFilters() default {};

	/**
	 * 指定是否应为扫描到的 bean 注册为延迟初始化。
	 * 默认为{@code false}；需要时将其切换为 {@code true}。
	 * <p>
	 * Specify whether scanned beans should be registered for lazy initialization.
	 * <p>Default is {@code false}; switch this to {@code true} when desired.
	 *
	 * @since 4.1
	 */
	boolean lazyInit() default false;


	/**
	 * Declares the type filter to be used as an {@linkplain ComponentScan#includeFilters
	 * include filter} or {@linkplain ComponentScan#excludeFilters exclude filter}.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target({})
	@interface Filter {

		/**
		 * 要使用的过滤器类型。默认ANNOTATION
		 * <p>
		 * The type of filter to use.
		 * <p>Default is {@link FilterType#ANNOTATION}.
		 *
		 * @see #classes
		 * @see #pattern
		 */
		FilterType type() default FilterType.ANNOTATION;

		/**
		 * Alias for {@link #classes}.
		 *
		 * @see #classes
		 */
		@AliasFor("classes")
		Class<?>[] value() default {};

		/**
		 * 用作过滤器的一个或多个类。
		 * 下表解释了如何根据 {@link #type} 属性的配置值解释类。
		 * <p>
		 * The class or classes to use as the filter.
		 * <p>The following table explains how the classes will be interpreted
		 * based on the configured value of the {@link #type} attribute.
		 * <table border="1">
		 * <tr><th>{@code FilterType}</th><th>Class Interpreted As</th></tr>
		 * <tr><td>{@link FilterType#ANNOTATION ANNOTATION}</td>
		 * <td>the annotation itself</td></tr>
		 * <tr><td>{@link FilterType#ASSIGNABLE_TYPE ASSIGNABLE_TYPE}</td>
		 * <td>the type that detected components should be assignable to</td></tr>
		 * <tr><td>{@link FilterType#CUSTOM CUSTOM}</td>
		 * <td>an implementation of {@link TypeFilter}</td></tr>
		 * </table>
		 * <p>When multiple classes are specified, <em>OR</em> logic is applied
		 * &mdash; for example, "include types annotated with {@code @Foo} OR {@code @Bar}".
		 * <p>Custom {@link TypeFilter TypeFilters} may optionally implement any of the
		 * following {@link org.springframework.beans.factory.Aware Aware} interfaces, and
		 * their respective methods will be called prior to {@link TypeFilter#match match}:
		 * <ul>
		 * <li>{@link org.springframework.context.EnvironmentAware EnvironmentAware}</li>
		 * <li>{@link org.springframework.beans.factory.BeanFactoryAware BeanFactoryAware}
		 * <li>{@link org.springframework.beans.factory.BeanClassLoaderAware BeanClassLoaderAware}
		 * <li>{@link org.springframework.context.ResourceLoaderAware ResourceLoaderAware}
		 * </ul>
		 * <p>Specifying zero classes is permitted but will have no effect on component
		 * scanning.
		 *
		 * @see #value
		 * @see #type
		 * @since 4.2
		 */
		@AliasFor("value")
		Class<?>[] classes() default {};

		/**
		 * 用于过滤器的模式（或模式），作为指定 Class {@link #value} 的替代方法。
		 * <p>
		 * The pattern (or patterns) to use for the filter, as an alternative
		 * to specifying a Class {@link #value}.
		 * <p>If {@link #type} is set to {@link FilterType#ASPECTJ ASPECTJ},
		 * this is an AspectJ type pattern expression. If {@link #type} is
		 * set to {@link FilterType#REGEX REGEX}, this is a regex pattern
		 * for the fully-qualified class names to match.
		 *
		 * @see #type
		 * @see #classes
		 */
		String[] pattern() default {};

	}

}
