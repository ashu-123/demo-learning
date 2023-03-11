package com.learning.demo.mapper;

import com.learning.demo.model.api.PersonApiDto;
import com.learning.demo.model.entity.Person;
import org.bson.types.ObjectId;

/**
 * The class which manages mapping from a person dto to entity & vice-versa.
 */
public class PersonMapper {

    private PersonMapper() {
    }

    public static Person toEntity(PersonApiDto personApiDto) {
        return new Person().setId(new ObjectId())
                   .setFirstName(personApiDto.getFirstName())
                   .setLastName(personApiDto.getLastName())
                   .setAge(personApiDto.getAge())
                   .setEmailId(personApiDto.getEmail());
    }

    public static PersonApiDto toDto(Person person) {
        return new PersonApiDto().setId(person.getId().toString())
                   .setFirstName(person.getFirstName())
                   .setLastName(person.getLastName())
                   .setAge(person.getAge())
                   .setEmail(person.getEmailId());
    }
}
