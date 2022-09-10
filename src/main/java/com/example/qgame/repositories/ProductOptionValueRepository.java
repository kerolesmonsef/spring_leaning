package com.example.qgame.repositories;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {

    @Query("DELETE FROM ProductOptionValue p WHERE p.product = :product")
    void deleteByProduct(Product product);
}
