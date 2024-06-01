package com.sonarplatform.programmingtest1rssfeed.controller;

import com.sonarplatform.programmingtest1rssfeed.service.ItemService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // kita buat mock bean dari properti ini,
    // nanti automatis spring akan membuat properti mocknya menggunakan mockito
    // lalu di registrasikan sebagai spring bean kedalam springnya.
    // jadi nanti di class manapun yang membutuhkan properti ini, dia akan menggunakan properti yang ini.
    // "Lantas gimana dengan properti yang sudah diimplementkan dan sudah diregistrasikan?" nah yang mock bean ini
    // nanti dia akan dijadi sebagai primary (dia yang akan pertama kali digunakan)
    // jadi nanti ketika kita jalankan unit testnya, yang di controller akan direplace dengan mock bean ini
    @MockBean
    private ItemService itemService;

    // ini seolah-olah akan menjadi class implement dari service tersebut
    @BeforeEach
    void setup() {
        // ketika service ini di panggil hellonya dengan parameter apapun itu, maka dia balikannya harus "Hello DNL!"
        Mockito.when(itemService.hello(Mockito.anyString()))
                .thenReturn("Hello DNL!");
        // ketika service ini di panggil hellonya dengan parameter null, maka dia balikannya harus "Hello Sonar!"
        Mockito.when(itemService.hello(null))
                .thenReturn("Hello Sonar!");
    }

    @Test
    void helloGuest() throws Exception {
        mockMvc.perform(
                get("/sonar/hello")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Sonar!"))
        );
    }

    @Test
    void helloName() throws Exception {
        mockMvc.perform(
                get("/sonar/hello").queryParam("name", "DNL")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello DNL!"))
        );
    }

    @Test
    void helloGuestHelloService() throws Exception {
        mockMvc.perform(
                get("/sonar/hello_mock_bean")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Sonar!"))
        );
    }

    @Test
    void helloNameHelloService() throws Exception {
        mockMvc.perform(
                get("/sonar/hello_mock_bean").queryParam("name", "DNL")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello DNL!"))
        );
    }

    @Test
    void paksaMethodPostDiMethodGet() throws Exception {
        mockMvc.perform(
                post("/sonar/hello_mock_bean").queryParam("name", "DNL")
        ).andExpectAll(
                status().isMethodNotAllowed()
        );
    }
}