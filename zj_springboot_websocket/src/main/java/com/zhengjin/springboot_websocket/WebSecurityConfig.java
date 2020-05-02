package com.zhengjin.springboot_websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 1. 对 / 和 /login 不拦截 2. 登录页面访问的路径为 /login 3. 登录成功后转向路径 /chat
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/chat").permitAll().and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("zhengjin").password("abce").roles("USER").and().withUser("spring")
				.password("spring").roles("USER");
	}

	/**
	 * /resources/static 目录下的静态资源不拦截。
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/static/**");
	}

}
