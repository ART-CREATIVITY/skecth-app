package io.artcreativity.sketchapp.metiers;

import java.util.List;

import io.artcreativity.sketchapp.models.Product;

public interface ProductManager {

    Product createProduct(Product product);
    List<Product> findAll();
}
