package com.learning.demo.repository;

import com.learning.demo.testdata.PersonTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static com.learning.demo.constant.PersonTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest // Replace with DataMongoTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveOperation() {
        var person = PersonTestData.getPerson();
        StepVerifier.create(personRepository.insert(person))
            .expectSubscription()
            .expectNextCount(1)
            .verifyComplete();
        StepVerifier.create(personRepository.findById(person.getId()))
            .expectSubscription()
            .consumeNextWith(c -> {
                assertAll(() -> assertEquals(TEST_FIRST_NAME, c.getFirstName()),
                    () -> assertEquals(TEST_LAST_NAME, c.getLastName()));
            })
            .verifyComplete();
    }
}