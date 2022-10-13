package io.artcreativity.sketchserver.controllers;

import io.artcreativity.sketchserver.entities.Product;
import io.artcreativity.sketchserver.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
