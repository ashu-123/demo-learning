package com.learning.demo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The class to configure spring beans for the application.
 */
@Configuration
@EnableConfigurationProperties(PersonConfiguration.class)
public class PersonBeanConfiguration {
}
