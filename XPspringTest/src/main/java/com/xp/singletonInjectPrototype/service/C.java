package com.xp.singletonInjectPrototype.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author xupan
 * @date 2021/08/09 16:55
 **/
@Component
@Scope("prototype")
public class C implements X {
}
