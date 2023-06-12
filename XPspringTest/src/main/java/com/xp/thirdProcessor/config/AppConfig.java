package com.xp.thirdProcessor.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xupan
 * @date 2021/06/24 17:15
 **/
@Configurable
@ComponentScan("com.xp.thirdProcessor.service")
public class AppConfig {
}
