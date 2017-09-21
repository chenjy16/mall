package com.meiduimall.service.member.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
/**
 * 获取当前环境其他service的地址
 * @author chencong
 *
 */
@Configuration
public class ServiceUrlProfileConfig {
	
	@Value("${service.account}")
	private String accountServiceUrl;

	public String getAccountServiceUrl() {
		return accountServiceUrl;
	}

	public void setAccountServiceUrl(String accountServiceUrl) {
		this.accountServiceUrl = accountServiceUrl;
	}

	
}
