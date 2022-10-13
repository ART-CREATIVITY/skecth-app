package io.artcreativity.sketchserver.repositories;

import io.artcreativity.sketchserver.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
