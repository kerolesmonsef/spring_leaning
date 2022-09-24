package com.example.qgame.helpers.converters.fromtoconverters;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPaymentMethodConverter implements Converter<String, PaymentMethod> {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod convert(String source) {
        return paymentMethodRepository.findById(Long.valueOf(source)).orElseThrow();
    }
}
