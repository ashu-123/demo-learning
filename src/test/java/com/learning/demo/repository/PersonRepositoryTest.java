package com.learning.demo.repository;

import com.learning.demo.model.entity.Person;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSaveOperation() {
        var objId = new ObjectId();
        var person = new Person().setId(objId)
                         .setFirstName("Ashutosh")
                         .setLastName("Mishra");
        StepVerifier.create(personRepository.insert(person))
            .expectSubscription()
            .expectNextCount(1)
            .verifyComplete();
        StepVerifier.create(personRepository.findById(objId))
            .expectSubscription()
            .consumeNextWith(c -> {
                System.out.println("yes i am here");
                assertAll(() -> assertEquals(c.getFirstName(), "Ashutosh"),
                          () -> assertEquals(c.getLastName(), "Mishra"));
            })
            .verifyComplete();
    }
}