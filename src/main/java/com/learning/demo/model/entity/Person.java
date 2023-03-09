package com.learning.demo.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {

    @Id
    private ObjectId id;

    private String firstName;

    private String lastName;

    private String emailId;

    private int age;

    public ObjectId getId() {
        return id;
    }

    public Person setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public Person setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                   "id=" + id +
                   ", firstName='" + firstName + '\'' +
                   ", lastName='" + lastName + '\'' +
                   ", emailId='" + emailId + '\'' +
                   ", age=" + age +
                   '}';
    }
}
