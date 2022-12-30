package com.example.qgame;

import com.example.qgame.Models.User;
import com.example.qgame.requests.LoginRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ActiveProfiles("test")
public class AuthinticationTests extends AbstractTest {

    private String uri = "/jwt/login";




    @Test
    public void testSuccessLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("user1@gmail.com");
        loginRequest.setPassword("1234");
        String inputJson = super.mapToJson(loginRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();

        JsonObject jsonObject = new JsonParser().parse(responseJson).getAsJsonObject();

        Assert.assertTrue(jsonObject.isJsonObject());
        Assert.assertEquals(jsonObject.get("status").getAsString(), "success");
        Assert.assertNotNull(jsonObject.get("token").getAsString());

    }

    @Test
    public void testFailCallLoginRequest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("user1@gmail.com");
        loginRequest.setPassword("12345");
        String inputJson = super.mapToJson(loginRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        JsonObject jsonObject = new JsonParser().parse(responseJson).getAsJsonObject();

        Assert.assertTrue(jsonObject.isJsonObject());
        Assert.assertEquals(jsonObject.get("status").getAsString(), "fail");
        Assert.assertEquals(jsonObject.get("message").getAsString(), "unauthorized");
    }

    @Test
    public void testEmailNotFoundValidation() throws Exception{
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(null);
        loginRequest.setPassword("12345");
        String inputJson = super.mapToJson(loginRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(mvcResult.getResponse().getStatus(), 400);
    }
}
