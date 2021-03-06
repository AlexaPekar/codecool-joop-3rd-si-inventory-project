package com.codecool;

import java.io.File;
import java.util.Scanner;

public class CsvMain {

    private static Scanner scanner = new Scanner(System.in);
    private static StoreManager storeManager = new StoreManager();
    private static PersistentCsvStore persistentCsvStore = new PersistentCsvStore();

    public static void main(String[] args) {

        storeManager.addStorage(persistentCsvStore);

        persistentCsvStore.setFileName("product.csv");

        File file = new File("product.csv");
        if (file.exists()) {
            persistentCsvStore.loadProducts();
        }
        while (true) {

            System.out.println("(1) Add new product");
            System.out.println("(2) List products");
            System.out.println("(0) Exit");
            System.out.println("Choose an option!");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Enter the type of the product (CD/book)!");
                    scanner.nextLine();
                    String type = scanner.nextLine().toLowerCase();
                    System.out.println("Enter the name of the product!");
                    String name = scanner.nextLine();
                    System.out.println("Enter the price of the product!");
                    int price = scanner.nextInt();
                    if (type.equals("cd")) {
                        System.out.println("Enter the number of tracks of the CD!");
                    } else if (type.equals("book")) {
                        System.out.println("Enter the number of the pages of the book!");
                    }
                    int size = scanner.nextInt();
                    try {
                        if (type.equals("cd")) {
                            storeManager.addCDProduct(name, price, size);
                        } else if (type.equals("book")) {
                            storeManager.addBookProduct(name, price, size);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                    break;
                case 2:
                    System.out.println(storeManager.listProducts());
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }

    }
}
