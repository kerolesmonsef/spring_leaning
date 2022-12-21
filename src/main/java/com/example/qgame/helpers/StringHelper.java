package com.example.qgame.helpers;

import java.sql.Timestamp;

public class StringHelper {

    public static String random(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomTicket() {
        return new Timestamp(System.currentTimeMillis()).getTime() + "-" + StringHelper.random(4);
    }

}
