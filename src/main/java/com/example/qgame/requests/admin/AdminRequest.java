package com.example.qgame.requests.admin;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class AdminRequest {

    @NonNull
    private String name;

    private List<Long> roles = new ArrayList<>();

    private List<Long> permissions = new ArrayList<>();
}
