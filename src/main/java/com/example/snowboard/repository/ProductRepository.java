package com.example.snowboard.repository;

import com.example.snowboard.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByModel(String model);
}
