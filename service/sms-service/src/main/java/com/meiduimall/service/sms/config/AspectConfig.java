package com.meiduimall.service.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meiduimall.core.advice.MethodLogAdvice;

@Configuration
public class AspectConfig {

	@Bean
	public MethodLogAdvice myAspect() {
		return new MethodLogAdvice();
	}
}
