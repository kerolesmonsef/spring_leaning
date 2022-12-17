package com.example.qgame.services;

import com.example.qgame.Models.Order;
import com.example.qgame.Models.Payment;
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
import com.example.qgame.repositories.PaymentMethodRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentGatewayFactory paymentGatewayFactory;

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

        orderDetailService.saveMany(orderDescriptor.getOrderDetails(), order);

        return order;
    }


    public ResponseEntity clientCreateOrder(CreateOrderRequest request, User user) throws Exception {

        Response response = new Response();

        PaymentMethod paymentMethod = paymentMethodRepository.getById(request.getPaymentMethodId());

        OrderCreator orderCreator = new OrderCreator(request, user, paymentMethod, orderDetailService.orderItemToOrderDetails(request.getOrderItemRequests()))
                .setOrderService(this);

        // check validation of order Creator if any then return

        if (paymentMethod.isOnline()) {

            IPaymentService service = new PayOrderPaymentService(
                    user,
                    orderCreator.getOrderDescriptor()
            );

            IPaymentGateway paymentGateway = paymentGatewayFactory.create(paymentMethod.getClassName(), user, service);

            Payment payment = paymentService.create(service,paymentMethodRepository.findByName(paymentGateway.getName()));

            IPaymentResponse paymentResponse = paymentGateway.gatewayResponse(payment);
            response.addAll(paymentResponse.toResource());

        } else { // cash on delivery

            Order order = orderCreator.create();
            response.add("order", order);
        }

        return response.toResponseEntity();
    }
}
