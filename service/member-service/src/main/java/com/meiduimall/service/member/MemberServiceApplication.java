package com.meiduimall.service.member;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableHystrix
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableEurekaClient
public class MemberServiceApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args){
		SpringApplication.run(MemberServiceApplication.class, args);
	}
}
