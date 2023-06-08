package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig<BCryptPasswordEncoder> extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private org.springframework.security.crypto.password.PasswordEncoder PasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
//		auth.userDetailsService(null)
		
		//create databases connection
		.dataSource(dataSource)
		
		//fetch uname, upassword, enamble using username input in login page
		.usersByUsernameQuery("select uname,upwd,uenable from usertab where uname=?")
		
		//fetch uname, urole using username input in login page (role)
		.authoritiesByUsernameQuery("select uname,urole from usertab where uname=?")
		
		//provide password encoder reference
		.passwordEncoder(PasswordEncoder)
		;
	}
	
	@Override   
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/home").permitAll() //for all users  no security required
		.antMatchers("/welcome").authenticated() // after login
		.antMatchers("/admin").hasAuthority("ADMIN") // only for admin
		.antMatchers("/emp").hasAuthority("ADMIN") // only for employee
		.antMatchers("/std").hasAuthority("STUDENT") // only for student
		.anyRequest().authenticated()
		
		//after login
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome",true)
		
		// for logout
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		// for exceptional handle
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		
		;
	}
}
