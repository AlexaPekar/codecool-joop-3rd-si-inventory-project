package com.codecool;

public class PersistentCsvStore extends CsvStore {

    @Override
    public void storeProduct(Product product) {
        products.add(product);

    }

}
