package com.xp.singletonInjectPrototype.config;

import com.xp.populateProperty.post_processor.OrderServiceBeanFactoryPostProcessor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author xupan
 * @date 2021/06/24 17:15
 **/
@Configurable
@ComponentScan("com.xp.singletonInjectPrototype.service")
public class AppConfig {
}
