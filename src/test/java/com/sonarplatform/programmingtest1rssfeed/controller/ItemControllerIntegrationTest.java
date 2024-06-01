package com.sonarplatform.programmingtest1rssfeed.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerIntegrationTest {
    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void helloGuest() throws Exception {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/sonar/hello", String.class);
        String body = responseEntity.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertEquals("Hello Sonar!", body.trim());
    }

    @Test
    void helloName() throws Exception {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/sonar/hello?name=DNL", String.class);
        String body = responseEntity.getBody();
        Assertions.assertNotNull(body);
        Assertions.assertEquals("Hello DNL!", body.trim());
    }
}