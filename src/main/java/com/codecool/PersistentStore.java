package com.codecool;

import java.util.List;

public class PersistentStore extends Store {

    @Override
    public void storeProduct(Product product) {
        products.add(product);

    }

}
