package com.example.qgame.helpers.entityembadable;

public class TestFile implements ITestInter {
    private String name;

    public TestFile() {

    }

    public TestFile(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getShit(){
        return name;
    }
}
