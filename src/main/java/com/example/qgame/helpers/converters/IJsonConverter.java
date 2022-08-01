package com.example.qgame.helpers.converters;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import javax.persistence.AttributeConverter;


public class IJsonConverter<T> implements AttributeConverter<T, String> {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private final TypeReference<T> typeReference = new TypeReference<T>() {
    };


    static {
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, typeReference);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
