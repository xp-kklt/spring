package com.xp.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author xupan
 * @date 2021/12/16 00:09
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface XpService {
	String value() default "";
}
