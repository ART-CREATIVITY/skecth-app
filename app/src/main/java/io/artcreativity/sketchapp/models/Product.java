package io.artcreativity.sketchapp.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    public static final String TABLE = "products";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS products " +
            "(id Integer PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "description TEXT NOT NULL," +
            "image TEXT," +
            "author_id INTEGER DEFAULT 0," +
            "stock_available DOUBLE DEFAULT 0," +
            "price DOUBLE DEFAULT 0)";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_AUTHOR_ID = "author_id";
    public static final String COLUMN_STOCK_AVAILABLE = "stock_available";
    public static final String COLUMN_PRICE = "price";


    public long id;
    public String name;
    public String description;
    public String image;
    public Profile author;
    public double stockAvailable;
    public BigDecimal price;

    public Product() {
    }

    public Product(long id, String name, String description, String image, Profile author,
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
