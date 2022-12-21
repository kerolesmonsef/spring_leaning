package com.example.qgame.helpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Data
@Accessors(chain = true)
public class HttpSender {

    private String url = "";

    private String requestBody = "";

    private String method = "POST";

    public JsonObject send() throws Exception {
        URL url = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(this.method);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
        BufferedReader br;
        try {

            br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            br = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

//        System.out.println("--response:");
//        System.out.println(response.toString());
        JsonObject responseJsonObject = (new JsonParser()).parse(response.toString()).getAsJsonObject();
        return responseJsonObject.getAsJsonObject();
    }
}
