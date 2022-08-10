package com.example.qgame.helpers;

public class Helper {

    public static String limit(String text, int limit) {
        if (text.length() > limit) {
            return text.substring(0, limit) + " ...";
        }

        return text;
    }
}
