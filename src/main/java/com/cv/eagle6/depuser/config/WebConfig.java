package com.cv.eagle6.depuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.cv.eagle6.depuser.interceptor.LoggerInterceptorAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	LoggerInterceptorAdapter loggerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor);
	}

}