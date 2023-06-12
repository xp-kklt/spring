package com.xp.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author xupan
 * @date 2021/12/16 00:11
 **/
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XpRequestMapping {
	String value() default "";
}
