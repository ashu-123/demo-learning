package com.learning.demo.service;

import com.learning.demo.testdata.PersonApiDtoTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Test
    void givenPerson_whenValid_thenCreatePerson() {
        StepVerifier.create(personService.createPerson(PersonApiDtoTestData.getPersonApiDto()))
            .expectSubscription()
            .consumeNextWith(person -> {
                assertAll(() -> assertEquals("ashutosh@gmail.com", person.getEmailId()),
                    () -> assertEquals(27, person.getAge()));
            })
            .verifyComplete();
    }
}