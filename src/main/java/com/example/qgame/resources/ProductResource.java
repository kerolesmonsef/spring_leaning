package com.example.qgame.resources;

import com.example.qgame.Models.Product;

import java.util.Map;

public class ProductResource extends JsonResponseResource<Product> {

    public ProductResource(Product product) {
        super(product);
    }

    @Override
    public Map<String, Object> toArray() {
        return Map.ofEntries(
                Map.entry("id", object.getId()),
                Map.entry("url", object.getUrl()),
                Map.entry("price", object.getPrice()),
                Map.entry("title", object.getTitle()),
                Map.entry("description", object.getTitle()),
                Map.entry("imageUrl", object.firstImageUrl()),
                Map.entry("imageUrl2", object.images().get(1))
        );
    }
}
