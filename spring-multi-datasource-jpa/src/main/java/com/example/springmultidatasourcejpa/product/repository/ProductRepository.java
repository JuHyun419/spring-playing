package com.example.springmultidatasourcejpa.product.repository;

import com.example.springmultidatasourcejpa.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
