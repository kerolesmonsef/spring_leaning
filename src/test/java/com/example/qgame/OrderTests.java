package com.example.qgame;

import com.example.qgame.requests.CreateOrderRequest;
import com.example.qgame.requests.LoginRequest;
import com.example.qgame.requests.OrderItemRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class OrderTests extends AbstractTest {

    private String uri = "/orders/create";


    private String getUserToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("user1@gmail.com");
        loginRequest.setPassword("1234");
        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post("/jwt/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();

        JsonObject jsonObject = new JsonParser().parse(responseJson).getAsJsonObject();
        return jsonObject.get("token").getAsString();
    }

    @Test
    public void createSuccessOrder() throws Exception {
        this.setAuth();

        List<OrderItemRequest> orderItemRequests = new ArrayList<>();

        orderItemRequests.add(new OrderItemRequest().setQuantity(1).setProductId(1L));
        orderItemRequests.add(new OrderItemRequest().setQuantity(2).setProductId(2L));
        orderItemRequests.add(new OrderItemRequest().setQuantity(1).setProductId(3L));

        CreateOrderRequest request = new CreateOrderRequest();
        request.setPaymentMethodId(1L);
        request.setOrderItemRequests(orderItemRequests);
        String inputJson = super.mapToJson(request);
        String usreToken = this.getUserToken();

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer ye" + this.getUserToken())
                .content(inputJson))
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());

    }
}
