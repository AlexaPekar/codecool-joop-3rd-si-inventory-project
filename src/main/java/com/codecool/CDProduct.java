package com.codecool;

public class CDProduct extends Product {

    private int numOffTrack;

    public CDProduct(String name, int price, int numOffTrack) {
        super(name, price);
        this.numOffTrack = numOffTrack;
    }

    public int getNumOffTrack() {
        return numOffTrack;
    }
}
