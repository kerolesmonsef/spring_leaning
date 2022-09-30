package com.example.qgame.resources;


import java.util.Map;

abstract public class JsonResponseResource<T> {
    protected T object;

    public JsonResponseResource(T object){
        this.object = object;
    }

    public abstract Map<String,Object> toArray();

}
