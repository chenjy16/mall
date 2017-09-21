package com.meiduimall.service;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableHystrix
@EnableEurekaClient
public class SettlementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SettlementApplication.class, args); 
	}
	

}
