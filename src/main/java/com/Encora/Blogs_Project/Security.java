package com.Encora.Blogs_Project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

	
@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable().authorizeHttpRequests(auth->auth .requestMatchers(HttpMethod.GET,"/blog").permitAll()
				.anyRequest().authenticated()).oauth2Login(auth->auth.defaultSuccessUrl("/blog"));
	
		return httpSecurity.build();
		
		
	}

}
