package com.example.qgame.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/payment")
public class PaymentController {

    @RequestMapping(value = "/{paymentName}/webhook", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.PUT})
    public Object webhook() {


        return null;
    }
}
