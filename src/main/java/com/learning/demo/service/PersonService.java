package com.learning.demo.service;

import com.learning.demo.ApplicationContextProvider;
import com.learning.demo.config.PersonConfiguration;
import com.learning.demo.mapper.PersonMapper;
import com.learning.demo.model.api.PersonApiDto;
import com.learning.demo.model.springBeans.PrototypeSpringBeanDto;
import com.learning.demo.model.entity.Person;
import com.learning.demo.model.springBeans.SingletonSpringBeanDto;
import com.learning.demo.repository.PersonRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonConfiguration personConfiguration;

    private final PrototypeSpringBeanDto prototypeSpringBeanDto;

    private final SingletonSpringBeanDto singletonSpringBeanDto;

    private final ApplicationContextProvider applicationContextProvider;

    public PersonService(PersonRepository personRepository,
                         PersonConfiguration personConfiguration,
                         PrototypeSpringBeanDto prototypeSpringBeanDto,
                         SingletonSpringBeanDto singletonSpringBeanDto,
                         ApplicationContextProvider applicationContextProvider) {
        this.personRepository = personRepository;
        this.personConfiguration = personConfiguration;
        this.prototypeSpringBeanDto = prototypeSpringBeanDto;
        this.singletonSpringBeanDto = singletonSpringBeanDto;
        this.applicationContextProvider = applicationContextProvider;
    }

    public Mono<Person> findPerson(String id) {
        return personRepository.findById(new ObjectId(id));
    }

    public Mono<Person> createPerson(PersonApiDto personApiDto) {
        return Mono.just(personApiDto)
                   .doOnNext(person -> System.out.println(singletonSpringBeanDto))
                   .doOnNext(person -> System.out.println(prototypeSpringBeanDto))
                   .doOnNext(person -> System.out.println(applicationContextProvider.getApplicationContext().containsBean("baseResource")))
                   .map(personDto -> personDto.setAge(personConfiguration.getAge()))
                   .map(personDto -> personDto.setEmail(personConfiguration.getEmailId()))
                   .map(PersonMapper::toEntity)
                   .flatMap(personRepository::insert)
                   .switchIfEmpty(Mono.error(new RuntimeException("fool")));
    }
}
