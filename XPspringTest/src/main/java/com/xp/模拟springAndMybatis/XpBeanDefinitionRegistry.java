package com.xp.模拟springAndMybatis;

import com.xp.模拟springAndMybatis.dao.XpDao;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author xupan
 * @date 2021/06/16 20:04
 **/
public class XpBeanDefinitionRegistry implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(XpScanner.class.getName());
		String[] packageNameArray = (String[]) annotationAttributes.get("value");
		String packageName = packageNameArray[0];
		System.out.println(packageName);

		//先在这里执行，注册一个bd,目的是把一些属性比如说包名保存进进PropertyValue中，然后在BeanDefinitionRegistryPostProcessor中
		//把这些属性值都取出来，然后创建bd,并赋值给bd,这样对于bd的修改就完成了。不过为什么不在这里就给bd赋值，非要放在后置处理器中呢？？？？？
		BeanDefinitionBuilder builder =
				BeanDefinitionBuilder.genericBeanDefinition(XpBeanDefinitionRegistryPostProcessor.class);
		builder.addPropertyValue("packageName", packageName);
		builder.addPropertyValue("clazz", XpFactoryBean.class);
		registry.registerBeanDefinition("xxx", builder.getBeanDefinition());
		//	builder.addPropertyValue();
		//有了packageName包名，就可以遍历包，找到所有的dao,注册进入bdmap

	}
}
