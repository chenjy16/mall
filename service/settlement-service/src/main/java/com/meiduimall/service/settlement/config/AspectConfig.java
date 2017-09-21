package com.meiduimall.service.settlement.config;

import org.springframework.context.annotation.Bean;

import com.meiduimall.core.advice.MethodLogAdvice;


public class AspectConfig {
	
	@Bean
    public MethodLogAdvice myAspect() {
        return new MethodLogAdvice();
    }


}
