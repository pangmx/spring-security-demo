package com.mx.springsecuritydemo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.mx.springsecuritydemo")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig {

	//setup variable to hold the properties, holds data read from the properties file
	@Autowired
	private Environment env;
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	
	//define bean for view resolver
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = 
				new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
				
	}
	
	//define bean for security datasource
	@Bean
	public DataSource securityDataSource() {
		//create connection pool
		ComboPooledDataSource securityDataSource = 
				new ComboPooledDataSource();
		
		//set up jdbc driver
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		//log the connection props
		logger.info(">>>>>>>>> JDBC URL: "+env.getProperty("jdbc.url"));
		logger.info(">>>>>>>>> JDBC User: "+env.getProperty("jdbc.user"));
		
		//set up database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool props
		securityDataSource.setInitialPoolSize(getIntPropValue("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntPropValue("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntPropValue("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntPropValue("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}

	private int getIntPropValue(String propName) {
		
		String propValue = env.getProperty(propName);
		return Integer.parseInt(propValue);
		
	}
}
