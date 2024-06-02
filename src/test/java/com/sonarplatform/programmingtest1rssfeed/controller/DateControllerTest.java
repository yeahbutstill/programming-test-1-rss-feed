package com.sonarplatform.programmingtest1rssfeed.controller;

import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void getDate() {
        mockMvc.perform(
                get("/yeahbutstill/date")
                        // ini fortmanya diambil dari class StringToDateConverter
                        .queryParam("date", "03-06-2024")
        ).andExpectAll(
                // tapi nanti responsenya itu harus sesuai dengan yang kita set di controllernya
                status().isOk(),
                content().string(Matchers.containsString("Date: " + "03062024"))
        );
    }
}