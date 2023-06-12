package com.xp.模拟springAndMybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xupan
 * @date 2021/06/16 18:45
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(XpBeanDefinitionRegistry.class)
//@Import(XpBeanDefinitionRegistryPostProcessor.class)
public @interface XpScanner {
	String[] value();
}
