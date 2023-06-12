package com.xp.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author xupan
 * @date 2021/12/16 00:14
 **/
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XpAutowired {
	String value() default "";
}
