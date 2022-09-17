package com.example.qgame.repositories;

import com.example.qgame.Models.Category;
import com.example.qgame.helpers.filters.products.results.CategoryIdResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c")
    List<Category> RandomN(Pageable pageable);

    @Query("SELECT new com.example.qgame.helpers.filters.products.results.CategoryIdResult(c.id ,c.name) FROM Category c")
    List<CategoryIdResult> getIdAndName();
}
