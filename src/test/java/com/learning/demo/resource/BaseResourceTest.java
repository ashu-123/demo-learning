package com.learning.demo.resource;

import com.learning.demo.model.api.PersonApiDto;
import com.learning.demo.repository.PersonRepository;
import com.learning.demo.testdata.PersonApiDtoTestData;
import com.learning.demo.testdata.PersonTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.learning.demo.constant.PersonTestConstants.*;
import static com.learning.demo.resource.constant.ResourceConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "10000")
class BaseResourceTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PersonRepository personRepository;

    @Test
    void givenPerson_whenFoundInDatabase_thenSuccessful() {
        var person = PersonTestData.getPerson();
        personRepository.insert(person).subscribe(System.out::println); // TODO: dont subscribe explicitly
        webTestClient.get()
            .uri(ROOT_PATH + FIND_PERSON, person.getId())
            .header("Content-Type", "application/x-ndjson")
            .header("Authorization", "Basic dXNlcjo5YmJlZWJjYS00MjdmLTQyOTMtYTE4YS1jMWZlNjc4YzQ1N2I")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(PersonApiDto.class)
            .consumeWith(c -> {
                var res = c.getResponseBody();
                assert res != null;
                assertAll(() -> assertEquals(TEST_FIRST_NAME, res.getFirstName()),
                    () -> assertEquals(TEST_LAST_NAME, res.getLastName()),
                    () -> assertEquals(person.getId().toString(), res.getId()));
            });
    }

    @Test
    void givenPerson_whenValid_thenCreatedSuccessfully() {
        var request = PersonApiDtoTestData.getPersonApiDto();
        webTestClient.post()
            .uri(ROOT_PATH+CREATE_PERSON_PATH)
            .header("Authorization", "Basic dXNlcjo5YmJlZWJjYS00MjdmLTQyOTMtYTE4YS1jMWZlNjc4YzQ1N2I")
            .bodyValue(request)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(PersonApiDto.class)
            .consumeWith(c -> {
                var res = c.getResponseBody();
                assert res != null;
                assertAll(()-> assertEquals(request.getFirstName(), res.getFirstName()),
                    () -> assertEquals(request.getLastName(), res.getLastName()),
                    () -> assertEquals(25, res.getAge()));
            });
    }
}
