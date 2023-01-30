package com.cargo.controller.tag;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.taglibs.standard.lang.jstl.test.PageContextImpl;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class DateTagTest {

    @Test
    void testConstructor() {
        DateTag actualDateTag = new DateTag();
        actualDateTag.setLocalDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        actualDateTag.setPattern("Pattern");
        assertNull(actualDateTag.getParent());
    }
}

