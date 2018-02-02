package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    BookProduct book;
    CDProduct cd;
    String cdName;
    String bookName;

    PersistentStore persistentStore = new PersistentStore();

    @BeforeEach
    void setUp() {
        book = new BookProduct("Bookocska", 20, 500);
        cd = new CDProduct("Cedeke", 8, 15);
        cdName = cd.getName();
        bookName = book.getName();
        persistentStore.setFileName("testProduct.xml");
    }

    @Test
    void saveToXml() {
        persistentStore.store(cd);
        persistentStore.store(book);
        assertEquals(cdName, persistentStore.loadProducts().get(0).getName());
        assertEquals(bookName, persistentStore.loadProducts().get(1).getName());
    }

    @Test
    void loadProducts() {
        assertEquals(cdName, persistentStore.loadProducts().get(0).getName());
        assertEquals(bookName, persistentStore.loadProducts().get(1).getName());
    }
}