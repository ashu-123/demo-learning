package com.learning.demo.model.api;


import jakarta.validation.constraints.NotNull;

public class PersonApiDto {

    private String id;

    @NotNull
    private String firstName;

    private String lastName;

    public String getId() {
        return id;
    }

    public PersonApiDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonApiDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonApiDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    public String toString() {
        return "PersonApiDto{" +
                   "id='" + id + '\'' +
                   ", firstName='" + firstName + '\'' +
                   ", lastName='" + lastName + '\'' +
                   '}';
    }
}
