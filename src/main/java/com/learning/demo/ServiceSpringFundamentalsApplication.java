package com.learning.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.learning.demo", proxyBeanMethods = false)
public class ServiceSpringFundamentalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSpringFundamentalsApplication.class, args);
	}

}
