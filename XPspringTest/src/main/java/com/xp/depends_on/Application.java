package com.xp.depends_on;

import com.xp.depends_on.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * @author xupan
 * @date 2021/08/06 11:53
 **/
public class Application {

	public static void main(String[] args) throws UnsupportedEncodingException {


		//int num = "01".getBytes("utf-8").length;
		//System.out.println(num);
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
		ac.register(AppConfig.class);
		ac.refresh();

		ac.close();

	}
}
