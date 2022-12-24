package com.example.qgame.thirdparties.payments.apis.paymob;

import com.example.qgame.Models.Payment;
import com.example.qgame.Models.User;
import com.example.qgame.helpers.HttpSender;
import com.example.qgame.thirdparties.payments.paymentclasses.IFramePaymentGateway;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentinfo.PaymentInfo;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IFramePaymentResponse;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.IPaymentResponse;
import com.example.qgame.thirdparties.payments.paymentclasses.paymentresponses.PaymentWebhookResponse;
import com.example.qgame.thirdparties.payments.paymentservices.IPaymentService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Component(value = "paymob-visa")
@Scope("prototype")
public class PaymobVisa extends IFramePaymentGateway {

    private static final String USERNAME = "01201533645";
    private static final String PASSWORD = "4y@W8G$r#$&H2Cx";

    public PaymobVisa(IPaymentService paymentService, User user) {
        super(paymentService, user);
    }

    @Override
    protected PaymentWebhookResponse innerWebhook() {


        return null;
    }

    @Override
    protected IPaymentResponse innerGatewayResponse(Payment payment) throws Exception {

        try {
            String authToken = getAuthinticationToken();

            String paymobOrderId = createOrederId(authToken, payment);

            String paymentToken = createPaymentToken(paymobOrderId, authToken, payment);


            return new IFramePaymentResponse(Map.ofEntries(
                    Map.entry("isSuccess", true),
                    Map.entry("url", String.format("https://accept.paymobsolutions.com/api/acceptance/iframes/%s?payment_token=%s", "712347", paymentToken)),
                    Map.entry("referenceCode", payment.getCode()),
                    Map.entry("apiResponse", "{}")
            ));
        } catch (Exception e) {
            return new IFramePaymentResponse(Map.ofEntries(
                    Map.entry("isSuccess", false),
                    Map.entry("errors", Collections.singletonList(e.getMessage()))
            ));
        }


    }

    @Override
    public String getName() {
        return "paymob-visa";
    }


    private String getAuthinticationToken() throws Exception {
        TreeMap treeData = new TreeMap<>();
        treeData.put("username", USERNAME);
        treeData.put("password", PASSWORD);
        String requestBody = new Gson().toJson(treeData);

        return new HttpSender()
                .setUrl("https://accept.paymob.com/api/auth/tokens")
                .setMethod("POST")
                .setRequestBody(requestBody)
                .send()
                .get("token")
                .getAsString();
    }

    private String createOrederId(String authToken, Payment payment) throws Exception {
        PaymentInfo paymentInfo = this.service.getPaymentInfo();

        TreeMap treeData = new TreeMap<>();
        treeData.put("auth_token", authToken);
        treeData.put("delivery_needed", "false");
        treeData.put("amount_cents", Math.round(paymentInfo.getTotal() * 100));
        treeData.put("currency", "EGP");
        treeData.put("merchant_order_id", payment.getCode());
        treeData.put("items", paymentInfo.paymobItemsToArray());

        String requestBody = new Gson().toJson(treeData);

        return new HttpSender()
                .setUrl("https://accept.paymob.com/api/ecommerce/orders")
                .setMethod("POST")
                .setRequestBody(requestBody)
                .send()
                .get("id")
                .getAsString();

    }

    private String createPaymentToken(String paymobOrderId, String authToken, Payment payment) throws Exception {
        PaymentInfo paymentInfo = payment.getInformation();

        TreeMap treeData = new TreeMap();
        treeData.put("auth_token", authToken);
        treeData.put("amount_cents", Math.round(paymentInfo.getTotal() * 100));
        treeData.put("expiration", 3600);
        treeData.put("order_id", paymobOrderId);

        TreeMap billingData = new TreeMap();
        billingData.put("apartment", "NA");
        billingData.put("email", this.user.getEmail());
        billingData.put("first_name", this.user.getName());
        billingData.put("last_name", this.user.getName());
        billingData.put("floor", "NA");
        billingData.put("phone_number", "+201201533645");
        billingData.put("street", "NA");
        billingData.put("building", "NA");
        billingData.put("shipping_method", "NA");
        billingData.put("postal_code", "NA");
        billingData.put("city", "NA");
        billingData.put("country", "NA");
        billingData.put("state", "NA");


        treeData.put("billing_data", billingData);
        treeData.put("currency", "EGP");
        treeData.put("integration_id", "3200435"); // payment method id from paymob dashboard
        treeData.put("lock_order_when_paid", "false");

        String requestBody = new Gson().toJson(treeData);

        return new HttpSender()
                .setUrl("https://accept.paymob.com/api/acceptance/payment_keys")
                .setMethod("POST")
                .setRequestBody(requestBody)
                .send()
                .get("token")
                .getAsString();
    }
}
