package com.example.qgame.helpers.ids;

import com.example.qgame.Models.Product;
import com.example.qgame.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductLikeId implements Serializable {
    @JoinColumn(name = "productId")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;
}
