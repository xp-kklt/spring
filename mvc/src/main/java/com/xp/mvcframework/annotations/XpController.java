package com.xp.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author xupan
 * @date 2021/12/16 00:06
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XpController {
	String value() default "";
}
