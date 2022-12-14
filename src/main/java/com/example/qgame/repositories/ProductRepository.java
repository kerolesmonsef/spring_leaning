package com.example.qgame.repositories;

import com.example.qgame.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("FROM Product p ORDER BY id DESC ")
    List<Product> getLastN(Pageable pageable);

    @Query(value = "FROM Product p ORDER BY RANDOM()")
        // RANDOM() FOR SQLite
    List<Product> getRandomN(Pageable pageable);

    @Query("SELECT p FROM Product p, ProductLike pl WHERE pl.id.product.id = p.id AND pl.id.user.id = :userId")
    List<Product> likes(@Param("userId") Long userId);

    @Query("FROM Product p WHERE p.id in :ids")
    List<Product> getByIds(@Param("ids") List<Long> productIds);
}
