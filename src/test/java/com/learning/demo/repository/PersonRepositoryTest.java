package com.learning.demo.repository;

import com.learning.demo.testdata.PersonTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static com.learning.demo.constant.PersonTestConstants.TEST_FIRST_NAME;
import static com.learning.demo.constant.PersonTestConstants.TEST_LAST_NAME;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataMongoTest
class PersonRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

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