package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.PaymentMethodRepository;

import java.util.ArrayList;
import java.util.Collection;

public class PaymentMethodSeeder extends ISeeder<PaymentMethod> {
    @Override
    protected Collection<PaymentMethod> data() {

        return new ArrayList<>() {{
            add(
                    new PaymentMethod()
                            .setName("opay-visa")
                            .setClassName("opay-visa")
                            .setDisplay_name("Visa - Master Card")
            );

            add(
                    new PaymentMethod()
                            .setName("vodafone-cash")
                            .setClassName("vodafone-cash")
                            .setDisplay_name("Vodafone cache")
            );
        }};
    }

    @Override
    public void seed() {
        QGameApplication.getBean(PaymentMethodRepository.class).saveAll(data());
    }
}
