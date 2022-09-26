package com.example.qgame.controllers.user;

import com.example.qgame.Models.User;
import com.example.qgame.controllers.IController;
import com.example.qgame.repositories.UserRepository;
import com.example.qgame.requests.CreateOrderRequest;
import com.example.qgame.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController extends IController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;


    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity store(@Valid @RequestBody CreateOrderRequest request) throws Exception {

        System.out.println(request);

        User user = userRepository.first();


        return orderService.clientCreateOrder(request, user);
    }
}
