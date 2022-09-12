package com.example.qgame.repositories;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductOptionValueRepository extends JpaRepository<ProductOptionValue, Long> {

    @Query("DELETE FROM ProductOptionValue p WHERE p.product.id = :product")
    @Modifying
    void deleteByProduct(@Param("product") Long product);
}
