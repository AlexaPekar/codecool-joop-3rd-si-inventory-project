package com.codecool;

import java.util.List;

public class StoreManager {

    private  StorageCapable storage;

    public void addStorage(StorageCapable storage) {

        this.storage = storage;
    }

    public void addCDProduct(String name, int price, int tracks) {
        storage.storeCDProducts(name, price, tracks);
    }

    public void addBookProduct(String name, int price, int pages) {
        storage.storeBookProduct(name, price, pages);
    }

    public String listProducts() {
        String listedProducts = "";
        List<Product> products = storage.getAllProduct();
        for (int i = 0; i < products.size(); i++) {
            if (i != products.size()-1) {
                listedProducts += products.get(i).getName() + ", ";
            } else {
                listedProducts += products.get(i).getName();
            }
        }
        return listedProducts;
    }

    public int getTotalProductPrice() {
        int totalPrice = 0;
        List<Product> products = storage.getAllProduct();
        for (int i = 0; i < products.size(); i++) {
            totalPrice += products.get(i).getPrice();
        }
        return totalPrice;
    }
}
