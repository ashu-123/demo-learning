package com.learning.demo.config;

import com.learning.demo.model.springBeans.PrototypeSpringBeanDto;
import com.learning.demo.model.springBeans.RequestSpringBeanDto;
import com.learning.demo.model.springBeans.SingletonSpringBeanDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

/**
 * The class to configure spring beans for the application.
 */
@Configuration
@EnableConfigurationProperties(PersonConfiguration.class)
public class PersonBeanConfiguration {

    @Bean
    @Scope
    public SingletonSpringBeanDto getSingletonSpringBeanDto() { return new SingletonSpringBeanDto(); }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PrototypeSpringBeanDto getPrototypeSpringBeanDto() { return new PrototypeSpringBeanDto(); }

    @Bean
    @RequestScope
    public RequestSpringBeanDto getRequestSpringBeanDto() { return new RequestSpringBeanDto(); }
}
