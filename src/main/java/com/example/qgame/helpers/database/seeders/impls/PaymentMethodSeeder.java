package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.PaymentMethodRepository;

import java.util.ArrayList;
import java.util.Collection;

public class PaymentMethodSeeder extends ISeeder<PaymentMethod> {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodSeeder(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }


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
                            .setClassName("wallet")
                            .setDisplay_name("Vodafone cache")
            );

            add(
                    new PaymentMethod()
                            .setName("paymob-visa")
                            .setClassName("paymob-visa")
                            .setDisplay_name("Visa - Master Card")
            );
        }};
    }

    @Override
    public void seed() {
        paymentMethodRepository.saveAll(data());
    }
}
