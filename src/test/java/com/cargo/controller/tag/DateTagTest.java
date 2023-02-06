package com.cargo.controller.tag;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;


class DateTagTest {

    @Test
    void testDateTagConstructor() {
        DateTag actualDateTag = new DateTag();
        actualDateTag.setLocalDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        actualDateTag.setPattern("Pattern");
        assertNull(actualDateTag.getParent());
    }
}

