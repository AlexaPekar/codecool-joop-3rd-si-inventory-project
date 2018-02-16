package com.codecool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CsvStore implements StorageCapable{


    List<Product> products = new ArrayList<Product>();
    String fileName;


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public List<Product> getAllProduct() {
        return products;
    }


    protected abstract void storeProduct(Product product);


    protected Product createProduct(String type, String name, int price, int size) {
        if (type.equals("CD")) {
            CDProduct newCd = new CDProduct(name, price, size);
            return newCd;

        } else {
            BookProduct newBook = new BookProduct(name, price, size);
            return newBook;
        }
    }

    public void store(Product product) throws IOException {
        storeProduct(product);
        saveToCsv();
    }




    public void storeCDProducts(String name, int price, int tracks) {
        Product cd = createProduct("CD", name, price, tracks);
        try {
            store(cd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void storeBookProduct(String name, int price, int pages) {
        Product book = createProduct("book", name, price, pages);
        try {
            store(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToCsv() throws IOException {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            StringBuilder sb = new StringBuilder();
            for (Product product : products) {
                if (product instanceof CDProduct) {
                    sb.append("CD" + ";");
                    sb.append(product.getName()).append(";");
                    sb.append(Integer.toString(product.getPrice())).append(";");
                    sb.append(Integer.toString(((CDProduct) product).getNumOfTrack()));
                } else if (product instanceof BookProduct) {
                    sb.append("book" + ";");
                    sb.append(product.getName()).append(";");
                    sb.append(Integer.toString(product.getPrice())).append(";");
                    sb.append(Integer.toString(((BookProduct) product).getNumOfPages()));
                }
                sb.append("\n");

            }
            String tempStr = sb.toString();
            tempStr = tempStr.substring(0, tempStr.length() - 1);
            pw.write(tempStr);
            pw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public List<Product> loadProducts() {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] attributes = line.split(";");
                if (attributes[0].equals("CD")) {
                    products.add(new CDProduct(attributes[1], Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3])));
                } else if (attributes[0].equals("book")) {
                    products.add(new BookProduct(attributes[1], Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3])));
                }
            }
            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return products;
    }

}
