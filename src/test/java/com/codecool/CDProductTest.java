package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CDProductTest {

    CDProduct cd;

    @BeforeEach
    void setUp() {
        cd = new CDProduct("Cedeke", 8, 12);
    }

    @Test
    void getName() {
        assertEquals("Cedeke", cd.getName());
    }

    @Test
    void getPrice() {
        assertEquals(8, cd.getPrice());
    }

    @Test
    void getNumOfTracks() {
        assertEquals(12, cd.getNumOfTrack());
    }
}