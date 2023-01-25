package com.cargo.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PriceMakerTest {

    private PriceMaker priceMakerUnderTest;

    @BeforeEach
    void setUp() {
        priceMakerUnderTest = new PriceMaker();
        priceMakerUnderTest.google = mock(ResourceBundle.class);
        priceMakerUnderTest.key = "key";
    }

    @Test
    void testGetPrice() {
        assertEquals(60, priceMakerUnderTest.getPrice(1, 1, 1, 1, 1));
    }

    @Test
    void testGetDistance() {
        assertEquals(0, priceMakerUnderTest.getDistance("origins", "destinations"));
    }
}
