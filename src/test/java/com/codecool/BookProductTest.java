package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookProductTest {


    BookProduct book;

    @BeforeEach
    void setUp() {
        book = new BookProduct("Bookocska", 15, 300);
    }

    @Test
    void getName() {
        assertEquals("Bookocska", book.getName());
    }

    @Test
    void getPrice() {
        assertEquals(15, book.getPrice());
    }

    @Test
    void getNumOfPages() {
        assertEquals(300, book.getNumOfPages());
    }
}