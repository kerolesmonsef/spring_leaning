package com.example.qgame.helpers.converters;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.QGameApplication;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import com.example.qgame.repositories.PaymentMethodRepository;

public class PaymentMethodDeserializer extends StdDeserializer<PaymentMethod> {
    protected PaymentMethodDeserializer(Class<?> vc) {
        super(vc);
    }

    protected PaymentMethodDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected PaymentMethodDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    @Override
    public PaymentMethod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String string = jsonParser.getText();

        return QGameApplication
                .getContext()
                .getBean(PaymentMethodRepository.class)
                .findById(Long.valueOf(string))
                .orElse(null);

    }
}
