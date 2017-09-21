package com.meiduimall.service.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.meiduimall.core.advice.MethodLogAdvice;

/**
 * aop配置，公共配置在meiduimall-common这个工程里面
 * 
 * @author yangchangfu
 *
 */
@Configuration
public class AspectConfig {

	@Bean
	public MethodLogAdvice myAspect() {
		return new MethodLogAdvice();
	}
}
