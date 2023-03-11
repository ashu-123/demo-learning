package com.learning.demo.config;

import com.learning.demo.config.condition.DemoCondition;
import com.learning.demo.model.springBeans.PrototypeSpringBeanDto;
import com.learning.demo.model.springBeans.RequestSpringBeanDto;
import com.learning.demo.model.springBeans.SingletonSpringBeanDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
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
    @ConditionalOnProperty(value = "bean.singleton.enabled", havingValue = "false")
    public SingletonSpringBeanDto getSingletonSpringBeanDto() { return new SingletonSpringBeanDto(); }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Conditional(DemoCondition.class)
    @ConditionalOnProperty(value = "bean.other", havingValue = "true")
    @ConditionalOnMissingBean(RequestSpringBeanDto.class)
    public PrototypeSpringBeanDto getPrototypeSpringBeanDto() { return new PrototypeSpringBeanDto(); }

    @Bean
    @RequestScope
    public RequestSpringBeanDto getRequestSpringBeanDto() { return new RequestSpringBeanDto(); }
}
