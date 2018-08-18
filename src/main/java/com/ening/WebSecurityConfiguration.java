package com.ening;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author Jeanyannick
 *
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select user_name,user_password, enable from users where user_name=?")
				.authoritiesByUsernameQuery("select user_name, enable from users where user_name=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/confirm","/register","/forgot","/reset","/css/**", "/js/**", "/images/**", "/bootstrap/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("user_name")
				.passwordParameter("user_password")
				.defaultSuccessUrl("/user")
				.permitAll()
				.failureUrl("/failLogin")
				.and()
			    .rememberMe().rememberMeParameter("remember-me").rememberMeCookieName("my-remember")
				.tokenValiditySeconds(86400).tokenRepository(tokenRepository()).and().csrf();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	  public PersistentTokenRepository tokenRepository() {
	    JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
	    jdbcTokenRepositoryImpl.setDataSource(dataSource);
	    return jdbcTokenRepositoryImpl;
	  }

}
