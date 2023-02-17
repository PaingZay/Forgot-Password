package com.example.WebAppClient.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.WebAppClient.Interceptor.SecurityInterceptor;

@Component
public class WebSecurityConfig implements WebMvcConfigurer{
	
	@Autowired
	SecurityInterceptor securityInterceptor;
	@Override
	public void addInterceptors(
	InterceptorRegistry registry) {

	registry.addInterceptor(securityInterceptor).addPathPatterns("/business/*");
	}
}
