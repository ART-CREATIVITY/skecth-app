package io.artcreativity.sketchapp.models;

import java.io.Serializable;

public class Product implements Serializable {

    public long id;
    public String name;
    public String description;
    public int image;
    public Profile author;
    public double stockAvailable;

    public Product() {
    }

    public Product(long id, String name, String description, int image, Profile author, double stockAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.author = author;
        this.stockAvailable = stockAvailable;
    }
}
