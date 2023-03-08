package com.learning.demo.service;

import com.learning.demo.mapper.PersonMapper;
import com.learning.demo.model.api.PersonApiDto;
import com.learning.demo.model.entity.Person;
import com.learning.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Mono<Person> createPerson(PersonApiDto personApiDto) {
        return Mono.just(personApiDto)
                   .map(PersonMapper::toEntity)
                   .flatMap(personRepository::insert);
    }
}
