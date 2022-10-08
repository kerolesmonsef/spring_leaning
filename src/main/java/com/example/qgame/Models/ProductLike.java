package com.example.qgame.Models;

import com.example.qgame.helpers.ids.ProductLikeId;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "product_like")
@Data
public class ProductLike {

    @EmbeddedId
    private ProductLikeId id;

    @CreatedDate
    private Date createdAt;
}
