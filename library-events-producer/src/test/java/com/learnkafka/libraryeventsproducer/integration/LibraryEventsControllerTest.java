package com.learnkafka.libraryeventsproducer.integration;

import com.learnkafka.libraryeventsproducer.domain.Book;
import com.learnkafka.libraryeventsproducer.domain.LibraryEvent;
import com.learnkafka.libraryeventsproducer.domain.LibraryEventType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibraryEventsControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void postLibraryEvent() {
        // given
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());

        HttpEntity<LibraryEvent> libraryEventHttpEntity = new HttpEntity<>(new LibraryEvent(null, LibraryEventType.NEW, new Book(123, "Trainspotting", "Irvine Welsh")), httpHeaders);

        ResponseEntity<LibraryEvent> responseEntity = testRestTemplate.exchange(
                "/v1/libraryevent",
                HttpMethod.POST,
                libraryEventHttpEntity,
                LibraryEvent.class);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

}