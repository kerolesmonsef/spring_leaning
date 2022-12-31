package com.example.qgame.requests.admin;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleRequest {
    private String name;
    private List<Long> permissions = new ArrayList<>();
}
