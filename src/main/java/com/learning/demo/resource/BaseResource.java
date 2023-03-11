package com.learning.demo.resource;

import com.learning.demo.mapper.PersonMapper;
import com.learning.demo.model.api.PersonApiDto;
import com.learning.demo.model.springBeans.PrototypeSpringBeanDto;
import com.learning.demo.model.springBeans.RequestSpringBeanDto;
import com.learning.demo.model.springBeans.SingletonSpringBeanDto;
import com.learning.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.learning.demo.resource.constant.ResourceConstants.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping(ROOT_PATH)
public class BaseResource {

    private final PersonService personService;

    private final SingletonSpringBeanDto singletonSpringBeanDto;

    private final PrototypeSpringBeanDto prototypeSpringBeanDto;

    public BaseResource(PersonService personService,
                        SingletonSpringBeanDto singletonSpringBeanDto,
                        PrototypeSpringBeanDto prototypeSpringBeanDto) {
        this.personService = personService;
        this.singletonSpringBeanDto = singletonSpringBeanDto;
        this.prototypeSpringBeanDto = prototypeSpringBeanDto;
    }

    @GetMapping(FIND_PERSON)
    @ResponseStatus(OK)
    public Mono<PersonApiDto> findPerson(@PathVariable String id) {
        return personService.findPerson(id)
                   .map(PersonMapper::toDto);
    }

    @PostMapping(CREATE_PERSON_PATH)
    @ResponseStatus(CREATED)
    public Mono<PersonApiDto> createPerson(@RequestBody @Valid PersonApiDto personApiDto) {
        return personService.createPerson(personApiDto)
                   .doOnNext(person -> System.out.println(singletonSpringBeanDto))
                   .doOnNext(person -> System.out.println(prototypeSpringBeanDto))
                   .map(PersonMapper::toDto);
    }
}
