package com.codecool;

import java.util.List;

public abstract class Store {

    private void saveToXml(Product product) {

    }

    protected void storeProduct(Product product) {

    }

    protected Product createProduct(String type, String name, int price, int size) {
        return null;
    }

    public List<Product> loadProducts() {
        return null;
    }

    public void store(Product product) {
        saveToXml(product);
        storeProduct(product);
    }
}
