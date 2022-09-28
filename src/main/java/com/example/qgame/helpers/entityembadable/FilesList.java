package com.example.qgame.helpers.entityembadable;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

    public String get(int index) {
        if (index >= 0 && index < files.size()) {
            return prefixUrl + files.get(index);
        }
        return "";
    }

    @JsonIgnore
    public List<String> getNativeFiles() {
        return this.files;
    }

    public List<String> getFiles() {
        return files.stream().map((file) -> this.prefixUrl + file).toList();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FilesList other = (FilesList) obj;
        return this.files.size() == other.files.size();
    }
}
