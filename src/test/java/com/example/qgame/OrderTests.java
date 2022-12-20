package com.example.qgame;

import com.example.qgame.Models.User;
import com.example.qgame.repositories.UserRepository;
import com.example.qgame.requests.CreateOrderRequest;
import com.example.qgame.requests.LoginRequest;
import com.example.qgame.requests.OrderItemRequest;
import com.example.qgame.services.OrderService;
import com.example.qgame.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
public class OrderTests extends AbstractTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void createSuccessOrder() throws Exception {

        List<OrderItemRequest> orderItemRequests = new ArrayList<>();

        orderItemRequests.add(new OrderItemRequest().setQuantity(1).setProductId(1L));
        orderItemRequests.add(new OrderItemRequest().setQuantity(2).setProductId(2L));
        orderItemRequests.add(new OrderItemRequest().setQuantity(1).setProductId(3L));

        CreateOrderRequest request = new CreateOrderRequest();
        request.setPaymentMethodId(1L);
        request.setOrderItemRequests(orderItemRequests);

        User user = userRepository.findByEmail("user1@gmail.com").orElseThrow();
        ResponseEntity<Map<String, Object>> response = orderService.clientCreateOrder(request, user);

        Map<String, Object> responseMap = response.getBody();
        Assert.assertNotNull(responseMap);
        Assert.assertEquals(responseMap.get("status"),"success");
        Assert.assertNotNull(responseMap.get("url"));
    }
}
