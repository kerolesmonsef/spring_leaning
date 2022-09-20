package com.example.qgame.helpers;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    @Getter
    private final Map<String, Object> data = new HashMap<>();

    public Response() {
        this.setSuccess();
    }

    public static Response getInstance() {
        return new Response();
    }

    public Response add(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public Response setSuccess() {
        data.put("status", "success");
        return this;
    }

    public Response setFail() {
        data.put("status", "fail");
        return this;
    }

    public ResponseEntity<Map<String, Object> > responseEntity() {
        if (data.get("status") == "success") {
            return ResponseEntity.ok(data);
        }
        return ResponseEntity.badRequest().body(data);
    }
}