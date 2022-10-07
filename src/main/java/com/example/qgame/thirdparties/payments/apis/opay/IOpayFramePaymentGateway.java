package com.example.qgame.thirdparties.payments.apis.opay;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.Helper;
import com.example.qgame.thirdparties.payments.paymentclasses.IFramePaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IFramePaymentResponse;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.PaymentWebhookResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

import static com.example.qgame.helpers.Helper.base_url;

abstract public class IOpayFramePaymentGateway extends IFramePaymentGateway {
    protected abstract String getPayMethod();

    protected String getReturnUrl() {
        return Helper.base_url("payment/success");
    }


    public IOpayFramePaymentGateway(IPaymentService paymentService, User user) {
        super(paymentService, user);
    }


    @Override
    protected IPaymentResponse innerGatewayResponse(Payment payment) throws Exception {

        PaymentInfo paymentInfo = this.service.getPaymentInfo();

        float total = paymentInfo.getTotal() * 100;

        Gson gson = new Gson();
        TreeMap order = new TreeMap<>();
        order.put("country", "EG");
        order.put("reference", payment.getCode());

        TreeMap amount = new TreeMap<>();
        amount.put("currency", "EGP");
        amount.put("total", total);
        order.put("amount", amount);

        order.put("returnUrl", getReturnUrl());
        order.put("callbackUrl", base_url("payment/" + getName() + "/webhook"));
        order.put("cancelUrl", base_url("payment/cancel"));
        order.put("expireAt", 300);

        TreeMap userInfo = new TreeMap<>();
        userInfo.put("userEmail", user.getEmail());
        userInfo.put("userId", user.getId());
        userInfo.put("userMobile", user.getMobile());
        userInfo.put("userName", user.getUsername());
        order.put("userInfo", userInfo);

        order.put("productList", paymentInfo.OpayItemsToArray());
        order.put("payMethod", getPayMethod());

        String requestBody = gson.toJson(order);
        System.out.println("--request:");
        System.out.println(requestBody);

        String addr = this.config().get("url") + "/international/cashier/create";
        String merchantId = this.config().get("MerchantId");
        String publicKey = this.config().get("PublicKey");

        URL url = new URL(addr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Authorization", "Bearer " + publicKey);
        con.setRequestProperty("MerchantId", merchantId);
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        System.out.println("--response:");
        System.out.println(response.toString());
        JsonObject responseJsonObject = (new JsonParser()).parse(response.toString()).getAsJsonObject();
        JsonObject data = responseJsonObject.getAsJsonObject("data");



        return new IFramePaymentResponse(Map.ofEntries(
                Map.entry("isSuccess","SUCCESSFUL".equals(responseJsonObject.get("message").getAsString())),
                Map.entry("url",data.get("cashierUrl").getAsString()),
                Map.entry("referenceCode",data.get("reference").getAsString()),
                Map.entry("apiResponse",response.toString())
        ));
    }

    @Override
    protected PaymentWebhookResponse innerWebhook() {
        return null;
    }

    private Map<String, String> config() {
        return Map.ofEntries(
                Map.entry("MerchantId", "281821110334147"),
                Map.entry("PublicKey", "OPAYPUB16359485336960.5681200400940466"),
                Map.entry("SecretKey", "OPAYPRV16359485336960.015072364054719678"),
                Map.entry("url", "https://sandboxapi.opaycheckout.com/api/v1")
        );
    }
}
