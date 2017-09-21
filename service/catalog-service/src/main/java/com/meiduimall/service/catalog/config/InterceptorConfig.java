package com.meiduimall.service.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meiduimall.service.catalog.interceptor.MemIdInterceptor;

/**
 * 注册拦截器到上下文
 * 
 * @author changchangfu
 *
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	// 注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getTokenInterceptor()).addPathPatterns("/**");
	}
	
	@Bean
    public HandlerInterceptor getTokenInterceptor(){
        return new MemIdInterceptor();
    }
}
