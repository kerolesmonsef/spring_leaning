package com.example.qgame.services;

import com.example.qgame.Models.Order;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.Response;
import com.example.qgame.helpers.StringHelper;
import com.example.qgame.helpers.order.OrderDescriptor;
import com.example.qgame.repositories.OrderRepository;
import com.example.qgame.requests.CreateOrderRequest;
import com.example.qgame.thirdparties.payments.paymentclasses.IPaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.PaymentGatewayFactory;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import com.example.qgame.thirdparties.payments.paymentservices.services.PayOrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Transactional
    public Order store(OrderDescriptor orderDescriptor) {

        Order order = new Order();
        order
                .setCode(StringHelper.random(10))
                .setUser(orderDescriptor.getUser())
                .setNote(orderDescriptor.getNote())
                .setPaymentMethod(orderDescriptor.getPaymentMethod())
                .setProductsPrice(orderDescriptor.productsPrice())
                .setProductsPriceAfterDiscount(orderDescriptor.productsPriceAfterDiscount())
                .setTotalPrice(orderDescriptor.totalPrice());

        orderRepository.save(order);

        orderDetailService.saveMany(orderDescriptor.getOrderItems(), order);

        return order;
    }


    public ResponseEntity clientCreateOrder(CreateOrderRequest request, User user) throws Exception {

        Response response = new Response();
        PaymentMethod paymentMethod = request.getPaymentMethod();

        OrderCreator orderCreator = new OrderCreator(request, user)
                .setOrderService(this);

        // check validation of order Creator if any then return

        if (request.getPaymentMethod().isOnline()) {

            IPaymentService paymentService = new PayOrderPaymentService(
                    paymentMethod,
                    user,
                    orderCreator.getOrderDescriptor()
            );

            IPaymentGateway paymentGateway = PaymentGatewayFactory
                    .create(paymentMethod, user, paymentService);

            IPaymentResponse paymentResponse = paymentGateway.gatewayResponse();
            response.addAll(paymentResponse.toResource());

        } else { // cash on delivery

            Order order = orderCreator.create();
            response.add("order", order);
        }

        return response.responseEntity();
    }
}
