package com.sonarplatform.programmingtest1rssfeed.service.impl;

import com.sonarplatform.programmingtest1rssfeed.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemServiceImplTest {
    @Autowired
    private ItemService itemService;

    @Test
    void hello() {
        Assertions.assertEquals("Hello Sonar!", itemService.hello(null));
        Assertions.assertEquals("Hello DNL!", itemService.hello("DNL"));
    }
}