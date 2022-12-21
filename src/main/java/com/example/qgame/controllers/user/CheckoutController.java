package com.example.qgame.controllers.user;

import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @GetMapping
    public ModelAndView checkout() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.getIsActive();


        return new ModelAndView("checkout")
                .addObject("paymentMethods", paymentMethods);
    }
}
