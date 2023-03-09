package com.learning.demo.service;

import com.learning.demo.config.PersonConfiguration;
import com.learning.demo.mapper.PersonMapper;
import com.learning.demo.model.api.PersonApiDto;
import com.learning.demo.model.entity.Person;
import com.learning.demo.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonConfiguration personConfiguration;

    public PersonService(PersonRepository personRepository, PersonConfiguration personConfiguration) {
        this.personRepository = personRepository;
        this.personConfiguration = personConfiguration;
    }

    public Mono<Person> findPerson(String id) {
        return personRepository.findById(new ObjectId(id));
    }

    public Mono<Person> createPerson(PersonApiDto personApiDto) {
        return Mono.just(personApiDto)
                   .map(personDto -> personDto.setAge(personConfiguration.getAge()))
                   .map(personDto -> personDto.setEmail(personConfiguration.getEmailId()))
                   .map(PersonMapper::toEntity)
                   .flatMap(personRepository::insert);
    }
}
