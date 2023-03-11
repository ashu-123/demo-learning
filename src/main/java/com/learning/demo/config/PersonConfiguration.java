package com.learning.demo.config;

import jakarta.validation.constraints.Email;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

/**
 * The binding class to load properties for person from configuration file.
 */
@Validated
@ConfigurationProperties(prefix = "person")
public class PersonConfiguration {

    @Email
    private String emailId;

    private int age;

    private PersonConfiguration() { }

    @ConstructorBinding
    public PersonConfiguration(String emailId, int age) {
        this.emailId = emailId;
        this.age = age;
    }

    public String getEmailId() { return emailId; }

    public int getAge() { return age; }

}
