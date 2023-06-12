package com.xp.模拟springAndMybatis.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xp.模拟springAndMybatis.XpScanner;
import com.xp.模拟springAndMybatis.dao.A;
import com.xp.模拟springAndMybatis.dao.B;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author xupan
 * @date 2021/06/16 18:42
 **/
@XpScanner("com.xp.模拟springAndMybatis.dao")
@MapperScan("com.xp.模拟springAndMybatis.dao")
@ComponentScan("com.xp.模拟springAndMybatis.dao")
@Configuration
public class AppConfig {


	@Bean
	public A a() {
		System.out.println("a init");
		return new A();
	}

	@Bean
	public B b() {
		a();
		return new B();
	}

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		// 关闭连接后不自动提交
		dataSource.setAutoCommitOnClose(false);

		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}

}
