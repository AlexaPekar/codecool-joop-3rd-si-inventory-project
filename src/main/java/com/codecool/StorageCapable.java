package com.codecool;

import java.util.List;

public interface StorageCapable {

    List<Product> getAllProduct();

    void storeCDProducts(String name, int price, int tracks);

    void storeBookProduct(String name, int price, int pages);

}
