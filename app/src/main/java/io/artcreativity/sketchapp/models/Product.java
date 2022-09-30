package io.artcreativity.sketchapp.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    public long id;
    public String name;
    public String description;
    public int image;
    public Profile author;
    public double stockAvailable;
    public BigDecimal price;

    public Product() {
    }

    public Product(long id, String name, String description, int image, Profile author,
                   double stockAvailable, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.author = author;
        this.stockAvailable = stockAvailable;
        this.price= price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", stockAvailable=" + stockAvailable +
                ", price=" + price +
                '}';
    }
}
