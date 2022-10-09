package com.example.qgame.resources;


import com.example.qgame.Models.Product;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

abstract public class JsonResponseResource<T> {
    protected T object;

    public JsonResponseResource(){

    }

    public JsonResponseResource(T object) {
        this.object = object;
    }

    public abstract Map<String, Object> toArray();

    public static <T extends JsonResponseResource, O> List<T> toCollection(Class<T> resourceClass, List<O> objects) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<T> resources = new ArrayList<>();

        for (O object : objects) {
            T resource = resourceClass.getConstructor(Object.class).newInstance(object);
            resources.add(resource);
        }

        return resources;
    }

}
