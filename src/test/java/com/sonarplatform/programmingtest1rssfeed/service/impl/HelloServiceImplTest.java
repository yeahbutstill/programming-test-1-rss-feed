package com.sonarplatform.programmingtest1rssfeed.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloServiceImplTest {
    @Autowired
    private HelloServiceImpl helloServiceImpl;

    @Test
    void hello() {
        Assertions.assertEquals("Hello Sonar!", helloServiceImpl.hello(null));
        Assertions.assertEquals("Hello DNL!", helloServiceImpl.hello("DNL"));
    }
}