package com.example.qgame.repositories;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.ProductLike;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.ids.ProductLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductLikeRepository extends JpaRepository<ProductLike, ProductLikeId> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ProductLike pl WHERE pl.id.user.id = :#{#user.id} AND pl.id.product.id = :#{#product.id}")
    void dislike(@Param("user") User user, @Param("product") Product product);
}
