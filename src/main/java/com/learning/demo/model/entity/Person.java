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

    @Override
    public String toString() {
        return "Person{" +
                   "id=" + id +
                   ", firstName='" + firstName + '\'' +
                   ", lastName='" + lastName + '\'' +
                   '}';
    }
}
