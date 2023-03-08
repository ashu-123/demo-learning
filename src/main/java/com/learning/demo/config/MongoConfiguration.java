package com.learning.demo.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import static com.learning.demo.config.constant.ConfigConstants.ROOT_REPO_BASE_PACKAGE;

/**
 * The class which enables reactive mongo repositories and manages configurations leveraging
 * the base class for reactive Spring Data MongoDB configuration using JavaConfig.
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = ROOT_REPO_BASE_PACKAGE)
public class MongoConfiguration extends AbstractReactiveMongoConfiguration {

    @Override
    @NotNull
    protected String getDatabaseName() { return "demo"; }
}
