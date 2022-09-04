package com.example.qgame.requests.admin;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AdminCreateUpdateCategoryRequest {
    private String name;
}
