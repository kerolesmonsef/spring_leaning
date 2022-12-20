package com.example.qgame;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.PaymentMethod;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.order.OrderDescriptor;
import com.example.qgame.repositories.PaymentMethodRepository;
import com.example.qgame.repositories.UserRepository;
import com.example.qgame.requests.OrderItemRequest;
import com.example.qgame.services.OrderDetailService;
import com.example.qgame.services.PaymentService;
import com.example.qgame.thirdparties.payments.apis.paymob.Paymob;
import com.example.qgame.thirdparties.payments.paymentservices.services.PayOrderPaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PaymoubTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testPaymoubPayment() throws Exception {
        PayOrderPaymentService service = getPaymentService();

        Payment payment = this.paymentService.create(service, service.getOrderDescriptor().getPaymentMethod());

        User user = userRepository.findFirstByOrderByIdAsc();

        Paymob paymoub = new Paymob(service, user);

        paymoub.gatewayResponse(payment);
    }


    private PayOrderPaymentService getPaymentService() {

        PaymentMethod paymentMethod = paymentMethodRepository.findById(3L).orElseThrow();

        List<OrderItemRequest> orderItemRequests = new ArrayList<>();

        orderItemRequests.add(new OrderItemRequest().setQuantity(1).setProductId(1L));
        orderItemRequests.add(new OrderItemRequest().setQuantity(2).setProductId(2L));
        orderItemRequests.add(new OrderItemRequest().setQuantity(1).setProductId(3L));


        User user = userRepository.findByEmail("user1@gmail.com").orElseThrow();

        OrderDescriptor orderDescriptor = new OrderDescriptor()
                .setPaymentMethod(paymentMethod)
                .setUser(user)
                .setOrderDetails(orderDetailService.orderItemToOrderDetails(orderItemRequests));

        return new PayOrderPaymentService(
                user,
                orderDescriptor
        );
    }
}
