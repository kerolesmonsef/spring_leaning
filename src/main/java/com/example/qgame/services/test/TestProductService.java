package com.example.qgame.services.test;

import com.example.qgame.Models.Product;
import com.example.qgame.repositories.ProductRepository;
import com.example.qgame.resources.ProductResource;
import org.springframework.stereotype.Service;

@Service
public class TestProductService {
    private final ProductRepository productRepository;
//
    public TestProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String getProductNameById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return product.getTitle();
        }

        return null;
    }
}
