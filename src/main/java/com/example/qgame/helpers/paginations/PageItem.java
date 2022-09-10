package com.example.qgame.helpers.paginations;

import lombok.Getter;
import lombok.experimental.Accessors;

public class PageItem {

    private String url;

    @Getter
    private int number;

    @Getter()
    @Accessors()
    private boolean isCurrent;

    public PageItem(String url, int number, boolean isCurrent) {
        this.number = number;
        this.isCurrent = isCurrent;
        this.url = url;
    }

    public int getNumber() {
        return number - 1;
    }

    public String getUrl() {
        return url + "?page=" + this.number;
    }

}