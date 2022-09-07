package com.example.qgame.repositories;

import com.example.qgame.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY id DESC ")
    List<Product> getLastN(Pageable pageable);

    @Query(value = "SELECT p from Product p ORDER BY RAND()")
    List<Product> getRandomN(Pageable pageable);
}
