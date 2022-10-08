package com.example.qgame.repositories;

import com.example.qgame.Models.ProductLike;
import com.example.qgame.helpers.ids.ProductLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikeRepository extends JpaRepository<ProductLike, ProductLikeId> {
}
