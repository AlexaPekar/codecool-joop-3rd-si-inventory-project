package com.codecool;


public class PersistentStore extends Store {

    @Override
    public void storeProduct(Product product) {
        products.add(product);

    }

}
