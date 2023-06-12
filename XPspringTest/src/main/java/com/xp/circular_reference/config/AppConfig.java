package com.xp.circular_reference.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xupan
 * @date 2021/06/24 17:15
 **/
@Configurable
@ComponentScan("com.xp.circular_reference.service")
public class AppConfig {
}
