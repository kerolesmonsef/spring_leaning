package com.example.qgame.helpers;

import com.example.qgame.resources.JsonResponseResource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    @Getter
    private final Map<String, Object> data = new HashMap<>();

    @Setter
    @Accessors(chain = true)
    private HttpStatus httpStatus = HttpStatus.OK;

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

    public Response addAll(Map<String, Object> map) {
        this.data.putAll(map);
        return this;
    }

    public Response add(String key, JsonResponseResource resource) {
        this.add(key, resource.toArray());
        return this;
    }

    public Response setSuccess() {
        data.put("status", "success");
        return this;
    }

    public Response setFail() {
        data.put("status", "fail");
        httpStatus = HttpStatus.BAD_REQUEST;
        return this;
    }

    public ResponseEntity responseEntity() {
        return new ResponseEntity(data, httpStatus);
    }
}