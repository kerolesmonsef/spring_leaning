package com.example.qgame.helpers.entityembadable;

import java.util.ArrayList;
import java.util.List;

public class FilesList {
    private List<String> files = new ArrayList<>();
    private String prefixUrl = "";

    public FilesList add(String file) {
        this.files.add(file);
        return this;
    }

    public void setPrefix(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public List<String> getFiles() {
        return files.stream().map((file) -> this.prefixUrl + file).toList();
    }
}
