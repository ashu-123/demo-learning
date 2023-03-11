package com.learning.demo.resource;

import com.learning.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.learning.demo.resource.constant.ResourceConstants.ROOT_PATH;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "10000")
class BaseResourceTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PersonService personService;

    @Test
    void whenGetName_thenSuccessful() {
        webTestClient.get()
            .uri(ROOT_PATH+"/name")
            .header("Content-Type", "application/x-ndjson")
            .header("Authorization", "Basic dXNlcjo5YmJlZWJjYS00MjdmLTQyOTMtYTE4YS1jMWZlNjc4YzQ1N2I")
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody(String.class)
            .consumeWith(c -> {
                var res = c.getResponseBody();
                System.out.println(res);
            });
    }
}
