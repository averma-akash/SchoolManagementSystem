package com.sms.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {
	
	private static final String[] AUTH_WHITELIST = {
	        "/authenticate",
	        "/swagger-resources/**",
	        "/swagger-ui/**",
	        "/v3/api-docs",
	        "/webjars/**"
	};
	
	@Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	
	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	        .exceptionHandling()
	        //.authenticationEntryPoint(unauthorizedHandler)
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll().and()
	        .authorizeRequests().antMatchers("/registration").permitAll()
	        .antMatchers("/api/test/**").permitAll()
	        .anyRequest().authenticated();
	    
	    //http.authenticationProvider(authenticationProvider());

	    //http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }
}
