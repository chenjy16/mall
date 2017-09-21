package com.meiduimall.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;





@EnableEurekaClient
@SpringBootApplication
@ComponentScan({ "com.meiduimall" })
public class PaymentApplication {


	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}
	
}
