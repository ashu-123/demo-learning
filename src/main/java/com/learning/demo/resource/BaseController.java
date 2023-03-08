package com.learning.demo.resource;

import com.learning.demo.mapper.PersonMapper;
import com.learning.demo.model.api.PersonApiDto;
import com.learning.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
@Validated
@RestController
@RequestMapping("/learning")
public class BaseController {

    private final PersonService personService;

    public BaseController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/name", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<String> getName() {
        return Flux.fromArray(new String[]{"Ashu", "Ashu", "Ashu", "Ashu", "Ashu"})
                   .delayElements(Duration.ofSeconds(2))
                   .doOnNext(System.out::println)
                   .map(String::toUpperCase);
    }

    @PostMapping("/create-person")
    public Mono<PersonApiDto> createPerson(@RequestBody @Valid PersonApiDto personApiDto) {
        return personService.createPerson(personApiDto)
                   .map(PersonMapper::toDto);
    }
}
