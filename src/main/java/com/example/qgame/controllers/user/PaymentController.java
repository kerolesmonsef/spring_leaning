package com.example.qgame.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/payment")
public class PaymentController {

    @RequestMapping(value = "/{paymentName}/webhook", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public Object webhook() {

        return null;
    }


    @GetMapping("/success")
    public Object success() {
        return new ModelAndView("thankyou");
    }
}
