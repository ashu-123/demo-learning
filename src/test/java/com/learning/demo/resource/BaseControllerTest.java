package com.learning.demo.resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "10000")
class BaseControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenGetName_thenGotName() {
        webTestClient.get()
            .uri("/learning/name")
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
