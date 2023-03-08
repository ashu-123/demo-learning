package com.learning.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.learning.demo.constant.CommonConstants.*;

@SpringBootApplication(scanBasePackages = BASE_PACKAGE, proxyBeanMethods = false)
public class ServiceSpringFundamentalsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSpringFundamentalsApplication.class, args);
    }

}
