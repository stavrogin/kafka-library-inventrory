package com.learnkafka.libraryeventsproducer.domain;

public record LibraryEvent(
        Integer libraryEventId,
        LibraryEventType libraryEventType,
        Book book
) {
}
