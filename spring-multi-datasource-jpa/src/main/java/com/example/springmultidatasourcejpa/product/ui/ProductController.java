package com.example.springmultidatasourcejpa.product.ui;

import com.example.springmultidatasourcejpa.product.entity.Product;
import com.example.springmultidatasourcejpa.product.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public void test() {
        productRepository.save(
                new Product(null, "Product1", 10000.0)
        );
    }
}
