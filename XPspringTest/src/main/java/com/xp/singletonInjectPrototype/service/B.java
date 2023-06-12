package com.xp.singletonInjectPrototype.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/08/09 16:39
 **/
@Component
@Scope("prototype")
//@Scope("singleton")
public class B implements X {
}
