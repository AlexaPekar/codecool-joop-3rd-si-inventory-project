package com.codecool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CsvStoreTest {

    BookProduct book;
    CDProduct cd;
    String cdName;
    String bookName;

    PersistentCsvStore persistentCsvStore = new PersistentCsvStore();

    @BeforeEach
    void setUp() {
        book = new BookProduct("Harwi Potah", 45, 489);
        cd = new CDProduct("Aqua", 3, 12);
        cdName = cd.getName();
        bookName = book.getName();
        persistentCsvStore.setFileName("testProduct.csv");
    }

    @Test
    void saveToXml() {
        try {
            persistentCsvStore.store(cd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            persistentCsvStore.store(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(cdName, persistentCsvStore.loadProducts().get(0).getName());
        assertEquals(bookName, persistentCsvStore.loadProducts().get(1).getName());
    }

    @Test
    void loadProducts() {
        assertEquals(cdName, persistentCsvStore.loadProducts().get(0).getName());
        assertEquals(bookName, persistentCsvStore.loadProducts().get(1).getName());
    }

}