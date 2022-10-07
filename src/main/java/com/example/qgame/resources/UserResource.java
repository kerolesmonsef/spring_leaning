package com.example.qgame.resources;

import com.example.qgame.Models.User;

import java.util.Map;

public class UserResource extends JsonResponseResource<User> {

    public UserResource(User object) {
        super(object);
    }

    @Override
    public Map<String, Object> toArray() {
        return Map.ofEntries(
                Map.entry("id", object.getId()),
                Map.entry("name", object.getName()),
                Map.entry("email", object.getEmail())
        );
    }
}
