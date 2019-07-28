package com.mx.springsecuritydemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class MySpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dbDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*//hardcoded users for initial testing purpose
		 * UserBuilder users = User.withDefaultPasswordEncoder();
		 * 
		 * auth.inMemoryAuthentication()
		 * .withUser(users.username("mary").password("test123").roles("EMPLOYEE","ADMIN"
		 * )) .withUser(users.username("john").password("test123").roles("EMPLOYEE",
		 * "MANAGER"))
		 * .withUser(users.username("susan").password("test123").roles("EMPLOYEE"));
		 */
		
		auth.jdbcAuthentication().dataSource(dbDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//configure to use login form and all requests need to be authenticated
		http.authorizeRequests()
				//.anyRequest().authenticated()
			//adding authorization based on roles and path
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
				.formLogin()
					.loginPage("/showLoginForm")
					.loginProcessingUrl("/authenticateUser")
					.permitAll()
				//add logout functionality
				.and()
					.logout()
					.permitAll()
				//configure exception handling when access is denied instead of showing scary 403 error page
				.and()
					.exceptionHandling().accessDeniedPage("/accessDenied");
		
		//permitAll indicates that the login and logout forms are accessible to all
	}
	
	

}
