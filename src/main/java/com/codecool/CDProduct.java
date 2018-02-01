package com.codecool;

public class CDProduct extends Product {

    private int numOfTrack;

    public CDProduct(String name, int price, int numOfTrack) {
        super(name, price);
        this.numOfTrack = numOfTrack;
    }

    public int getNumOfTrack() {
        return numOfTrack;
    }
}
